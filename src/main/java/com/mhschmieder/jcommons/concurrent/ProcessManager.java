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
package com.mhschmieder.jcommons.concurrent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcessManager {

    private static final Logger LOGGER = System.getLogger(
            ProcessManager.class.getName() );

    private static final HashMap< Integer, Process > processMap
            = new HashMap<>();

    private static final HashMap< Integer, BufferedWriter > inputMap =
            new HashMap<>();

    private static final HashMap< Integer, BufferedReader > outputMap =
            new HashMap<>();

    private static final ExecutorService executor =
            Executors.newCachedThreadPool( Executors.defaultThreadFactory() );

    private ProcessManager() {
    }

    /**
     * Gets a process handle if the hash code of the string matches in the
     * stored map of processes.
     *
     * @param processName The name used to get the hash code
     * @return The process stored from the {@code processName} hash code, or
     * {@code null} if it does not exist.
     */
    public static Process getProcess( final String processName ) {
        return processMap.getOrDefault(
                processName.hashCode(),
                null );
    }

    public static Process getProcess( final String processName,
                                      final String command ) {
        if ( processMap.containsKey( processName.hashCode() ) ) {
            return processMap.get( processName.hashCode() );
        }

        final ProcessBuilder processBuilder = new ProcessBuilder( command );
        try {
            processMap.put( processName.hashCode(), processBuilder.start() );
        } catch( final Exception e ) {
            LOGGER.log( Level.ERROR,
                    "Unable to start process: " + processName,
                    e );
            return null;
        }

        final Runnable remover = () -> {
            final Process process = getProcess( processName );
            while ( process.isAlive() );

            processMap.remove( processName.hashCode() );
            inputMap.remove( processName.hashCode() );
            outputMap.remove( processName.hashCode() );
        };

        executor.submit( remover );

        final Process process = processMap.get( processName.hashCode() );
        final BufferedWriter stdin = new BufferedWriter( new OutputStreamWriter(
                process.getOutputStream() ) );
        final BufferedReader stdout = new BufferedReader( new InputStreamReader(
                process.getInputStream() ) );
        inputMap.put( processName.hashCode(), stdin );
        outputMap.put( processName.hashCode(), stdout );

        return processMap.get( processName.hashCode() );
    }

    public static BufferedWriter getInputStream( final String processName ) {
        return inputMap.getOrDefault(
                processName.hashCode(), null );
    }

    public static BufferedReader getOutputStream( final String processName ) {
        return outputMap.getOrDefault(
                processName.hashCode(), null );
    }

    public static boolean closeStream( final String processName ) {
        if ( processMap.containsKey( processName.hashCode() ) ) {
            final Process process = processMap.get( processName.hashCode() );
            process.destroyForcibly();
            return true;
        }
        return false;
    }
}
