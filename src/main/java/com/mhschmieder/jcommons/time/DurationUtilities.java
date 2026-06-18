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

import org.apache.commons.math3.util.FastMath;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public final class DurationUtilities {

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private DurationUtilities() {}

    /**
     * Returns the timestep (index) based on the given time from start (seconds)
     * divided by the interval (seconds) between timesteps.
     * <p>
     * As timesteps start at zero (an AI tradition), they correspond to indices.
     *
     * @param timeFromStartSeconds the time from start (seconds) to convert
     * @param timestepDuration the duration of each timestep in a timeline
     * @return the timestep (index) for the given time from start (seconds)
     */
    public static int getTimestepIndex( final long timeFromStartSeconds,
                                        final Duration timestepDuration ) {
        return FastMath.toIntExact( ( long ) FastMath.floor( ( double )
                timeFromStartSeconds / timestepDuration.toSeconds() ) );
    }

    /**
     * Returns the time from start (seconds) based on the given timestep (index)
     * multiplied by the interval (seconds) between timesteps.
     * <p>
     * As timesteps start at zero (an AI tradition), we do not decrement before
     * multiplying as the resulting time offset is correct for all timesteps.
     *
     * @param timestepIndex the timestep (index from 0) to convert
     * @param timestepDuration the duration of each timestep in a timeline
     * @return the time from start (seconds) for the given timestep (index)
     */
    public static long getTimeFromStartSeconds(
            final int timestepIndex,
            final Duration timestepDuration ) {
        return timestepIndex * timestepDuration.toSeconds();
    }

    public static Duration getDurationFromText( final String durationText,
                                                final NumberFormat numberParse,
                                                final ChronoUnit chronoUnit )
            throws ParseException {
        Duration duration = Duration.ZERO;

        final Number numericValue = numberParse.parse( durationText );
        long longValue = numericValue.longValue();

        int indexOf = durationText.indexOf( '.' );

        switch ( chronoUnit ) {
            // Nanos are already in the lowest possible units so no reason to
            // process decimals.
            case NANOS -> duration = Duration.ofNanos( longValue );

            // Otherwise process decimal places and convert to nanos
            case MILLIS -> {
                duration = Duration.ofMillis( longValue );
                if ( indexOf >= 0 ) {
                    String roundoffMillis = durationText.substring( indexOf );

                    long extraValueNanos = ( long ) ( numberParse.parse(
                            roundoffMillis ).doubleValue()
                            * TimeConversion.MILLIS_TO_NANOS );

                    duration = duration.plus(
                            extraValueNanos, ChronoUnit.NANOS );
                }
            }
            case SECONDS -> {
                duration = Duration.ofSeconds( longValue );
                if ( indexOf >= 0 ) {
                    String roundoffSeconds = durationText.substring( indexOf );

                    long extraValueNanos = ( long ) ( numberParse.parse(
                            roundoffSeconds ).doubleValue()
                            * TimeConversion.SECONDS_TO_NANOS );

                    duration = duration.plus(
                            extraValueNanos, ChronoUnit.NANOS );
                }
            }
            case MINUTES -> {
                duration = Duration.ofMinutes( longValue );
                if ( indexOf >= 0 ) {
                    String roundoffMinutes = durationText.substring( indexOf );

                    long extraValueNanos = ( long ) ( numberParse.parse(
                            roundoffMinutes ).doubleValue()
                            * TimeConversion.MINUTES_TO_NANOS );

                    duration = duration.plus(
                            extraValueNanos, ChronoUnit.NANOS );
                }
            }
            case HOURS -> {
                duration = Duration.ofHours( longValue );
                if ( indexOf >= 0 ) {
                    String roundOffHours = durationText.substring( indexOf );

                    long extraValueNanos = ( long ) ( numberParse.parse(
                            roundOffHours ).doubleValue()
                            * TimeConversion.HOURS_TO_NANOS );

                    duration = duration.plus(
                            extraValueNanos, ChronoUnit.NANOS );
                }
            }
            case DAYS -> {
                duration = Duration.ofDays( longValue );
                if ( indexOf >= 0 ) {
                    String roundoffDays = durationText.substring( indexOf );

                    long extraValueNanos = ( long ) ( numberParse.parse(
                            roundoffDays ).doubleValue()
                            * TimeConversion.DAYS_TO_NANOS );

                    duration = duration.plus(
                            extraValueNanos, ChronoUnit.NANOS );
                }
            }
            default -> {}
        }

        return duration;
    }

    public static Duration getDurationFromSecondsDouble(
            final double valueSeconds ) {
        double valueNanos = valueSeconds * TimeConversion.SECONDS_TO_NANOS;

        return Duration.ofNanos( (long) FastMath.floor( valueNanos ) );
    }

    public static double getDoubleSecondsFromDuration(
            final Duration duration ) {
        long valueNanos = duration.toNanos();

        return valueNanos * TimeConversion.NANOS_TO_SECONDS;
    }
}
