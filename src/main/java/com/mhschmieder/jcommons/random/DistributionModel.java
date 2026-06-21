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

import com.mhschmieder.jcommons.lang.EnumUtilities;
import com.mhschmieder.jcommons.lang.Indexed;
import com.mhschmieder.jcommons.lang.Labeled;

/**
 * Enumeration of strategies for adjusting values based on a distribution model.
 */
public enum DistributionModel implements Indexed< DistributionModel >,
        Labeled< DistributionModel > {
    /** Unconstrained by min/max range or random distribution model */
    NONE( 0, "None (Unconstrained)" ),
    /** Constrained by min/max range sans distribution model */
    BOUNDED( 1, "Bounded (Constrained)" ),
    /** Constrained by min/max range with Uniform Distribution Model */
    UNIFORM_DISTRIBUTION( 2, "Uniform Distribution" ),
    /** Constrained by min/max range with Gamma Distribution Model */
    GAMMA_DISTRIBUTION( 3, "Gamma Distribution" );

    private final int index;
    private final String label;

    DistributionModel( final int pIndex,
                       final String pLabel ) {
        index = pIndex;
        label = pLabel;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public DistributionModel valueOfIndex( final int pIndex ) {
        return ( DistributionModel ) EnumUtilities.getIndexedEnumFromIndex(
                pIndex, values() );
    }

    @Override
    public String label() {
        return label;
    }

    @Override
    public DistributionModel valueOfLabel( final String text ) {
        return ( DistributionModel ) EnumUtilities.getLabeledEnumFromLabel(
                text, values() );
    }

    public static DistributionModel defaultValue() {
        return UNIFORM_DISTRIBUTION;
    }

    @Override
    public String toString() {
        // NOTE: This override takes care of displaying the current choice in
        //  its custom label form when a Combo Box is hosted by a Table Cell. It
        //  also addresses an issue with the Jackson parser if in a JSON file.
        return label();
    }
}
