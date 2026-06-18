/*
 * MIT License
 *
 * Copyright (c) 2026 Mark Schmieder. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is part of the jcommons Library
 *
 * You should have received a copy of the MIT License along with the jcommons
 * Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/jcommons
 */
package com.mhschmieder.jcommons.time;

public final class TimeConversion {

    /**
     * The default constructor is disabled, as this is a static constants class.
     */
    private TimeConversion() {}

    public static final double MILLIS_TO_NANOS = 1_000_000.0d;
    public static final double SECONDS_TO_NANOS = MILLIS_TO_NANOS * 1_000.0d;
    public static final double MINUTES_TO_NANOS = SECONDS_TO_NANOS * 60.0d;
    public static final double HOURS_TO_NANOS = MINUTES_TO_NANOS * 60.0d;
    public static final double DAYS_TO_NANOS = HOURS_TO_NANOS * 24.0d;

    public static final double NANOS_TO_MILLIS = 1.0d / MILLIS_TO_NANOS;
    public static final double SECONDS_TO_MILLIS = SECONDS_TO_NANOS
            / MILLIS_TO_NANOS;
    public static final double MINUTES_TO_MILLIS = MINUTES_TO_NANOS
            / MILLIS_TO_NANOS;
    public static final double HOURS_TO_MILLIS = HOURS_TO_NANOS
            / MILLIS_TO_NANOS;
    public static final double DAYS_TO_MILLIS = DAYS_TO_NANOS / MILLIS_TO_NANOS;

    public static final double NANOS_TO_SECONDS = 1.0d / SECONDS_TO_NANOS;
    public static final double MILLIS_TO_SECONDS = 1.0d / NANOS_TO_MILLIS;
    public static final double MINUTES_TO_SECONDS = MINUTES_TO_NANOS
            / SECONDS_TO_NANOS;
    public static final double HOURS_TO_SECONDS = HOURS_TO_NANOS
            / SECONDS_TO_NANOS;
    public static final double DAYS_TO_SECONDS = DAYS_TO_NANOS
            / SECONDS_TO_NANOS;

    public static final double NANOS_TO_MINUTES = 1.0d / MINUTES_TO_NANOS;
    public static final double MILLIS_TO_MINUTES = 1.0d / MINUTES_TO_MILLIS;
    public static final double SECONDS_TO_MINUTES = 1.0d / MINUTES_TO_SECONDS;
    public static final double HOURS_TO_MINUTES = HOURS_TO_NANOS
            / MINUTES_TO_NANOS;
    public static final double DAYS_TO_MINUTES = DAYS_TO_NANOS
            / MINUTES_TO_NANOS;

    public static final double NANOS_TO_HOURS = 1.0d / HOURS_TO_NANOS;
    public static final double MILLIS_TO_HOURS = 1.0d / HOURS_TO_MILLIS;
    public static final double SECONDS_TO_HOURS = 1.0d / HOURS_TO_SECONDS;
    public static final double MINUTES_TO_HOURS = 1.0d / HOURS_TO_MINUTES;
    public static final double DAYS_TO_HOURS = DAYS_TO_NANOS / HOURS_TO_NANOS;

    public static final double NANOS_TO_DAYS = 1.0d / DAYS_TO_NANOS;
    public static final double MILLIS_TO_DAYS = 1.0d / DAYS_TO_MILLIS;
    public static final double SECONDS_TO_DAYS = 1.0d / DAYS_TO_SECONDS;
    public static final double MINUTES_TO_DAYS = 1.0d / DAYS_TO_MINUTES;
    public static final double HOURS_TO_DAYS = 1.0d / DAYS_TO_HOURS;
}
