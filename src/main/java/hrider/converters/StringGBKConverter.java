package hrider.converters;

import org.apache.hadoop.hbase.util.Bytes;

import java.io.UnsupportedEncodingException;

/**
 * Copyright (C) 2012 NICE Systems ltd.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Igor Cher
 * @version %I%, %G%
 *          <p/>
 *          This class is responsible for converting data from byte[] to string and vice versa.
 */
public class StringGBKConverter extends TypeConverter {
    private static final String DEFAULT_CHARSET = "GBK";

    private static final long serialVersionUID = 8661833153430116861L;

    @Override
    public byte[] toBytes(String value) {
        if (value == null) {
            return EMPTY_BYTES_ARRAY;
        }
        return Bytes.toBytes(value);
    }

    @Override
    public String toString(byte[] value) {
        if (value == null) {
            return null;
        }
        try {
            return value == null ? null : toString(value, 0, value.length, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
            try {
                return value == null ? null : toString(value, 0, value.length, "UTF-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
                return "";
            }
        }
    }

    public static String toString(byte[] b, int off, int len, String charset) throws UnsupportedEncodingException {
        return b == null ? null : (len == 0 ? "" : new String(b, off, len, charset));
    }

    @Override
    public boolean canConvert(byte[] value) {
        try {
            Bytes.toString(value);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    @Override
    public boolean supportsFormatting() {
        return false;
    }

    @Override
    public boolean isValidForNameConversion() {
        return true;
    }
}
