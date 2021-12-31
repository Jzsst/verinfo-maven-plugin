
/*
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package cn.yzsen.plugin.maven.message.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


/**
 * The type Date util.
 *
 * @author yzsen
 * @classname: DateUtil
 * @title: Date util
 * @description: 时间格式化工具.
 * @date: 2021 /12/30 15:41
 * @copyright 2021 www.yzsen.cn Inc. All rights reserved.
 */
public class DateUtil {

    private static final String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_FORMAT_DATETIME_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * parseLocalDateTime.
     * out put format:yyyy-MM-dd HH:mm:ss
     *
     * @param str date String
     * @return yyyy -MM-dd HH:mm:ss
     * @see LocalDateTime
     */
    public static LocalDateTime parseLocalDateTime(final String str) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(DATE_FORMAT_DATETIME));
    }

    /**
     * Gets current date time.
     *
     * @return the current date time
     */
    public static String formatDateWithSecond() {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_DATETIME);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }


    /**
     * Format date with millisecond string.
     *
     * @param date the date
     * @return the string
     */
    public static String formatDateWithMillisecond(final Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_DATETIME_SSS);
        return df.format(date);
    }

}
