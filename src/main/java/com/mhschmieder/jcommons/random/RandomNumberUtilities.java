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
package com.mhschmieder.jcommons.random;

import org.apache.commons.rng.UniformRandomProvider;

import java.util.Collection;
import java.util.Random;

public final class RandomNumberUtilities {

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private RandomNumberUtilities() {}

    /**
     * Returns the next random integer that is a valid collection index.
     * <p>
     * This method guarantees that we always use a valid index between zero and
     * size-1, using a uniform distribution of equal probability for each index.
     *
     * @param randomProvider the Random instance to query for next random integer
     * @param collection the collection for which we need a valid index
     * @return the next random integer that is a valid collection index
     */
    public static int getRandomIndex(
            final UniformRandomProvider randomProvider,
            final Collection< ? > collection ) {
        return getRandomIndex( randomProvider, collection.size() );
    }

    /**
     * Returns the next random integer that is a valid collection index.
     * <p>
     * This method guarantees that we always use a valid index between zero and
     * size-1, using a uniform distribution of equal probability for each index.
     * <p>
     * For this overload of {@code getRandomIndex()}, the collection size is
     * passed in and can be from Collection.size() or from static array length.
     * <p>
     * NOTE: The call to {@code nextInt()} on {@link Random} applies the passed
     *  argument as an exclusive bound rather than an inclusive bound, so there
     *  is no need to subtract one from the collection's size.
     *
     * @param randomProvider the Random instance to query for next random integer
     * @param collectionSize the size of the collection we need an index for
     * @return the next random integer that is a valid collection index
     */
    public static int getRandomIndex(
            final UniformRandomProvider randomProvider,
            final int collectionSize ) {
        return randomProvider.nextInt( collectionSize );
    }
}
