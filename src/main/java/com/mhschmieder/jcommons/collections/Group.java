/*
 * MIT License
 *
 * Copyright (c) 2025, 2026 Mark Schmieder. All rights reserved.
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
package com.mhschmieder.jcommons.collections;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group< T extends GroupItem< T > > implements Item< Group< T > > {

    private static final Logger LOGGER = System.getLogger(
            Group.class.getName() );

    private static final String DEFAULT_LABEL
            = "New Group";

    private String label;

    private final List<T> items;

    public Group() {
        this( DEFAULT_LABEL );
    }

    public Group( final String label ) {
        this(
                label,
                new ArrayList<>() );
    }

    public Group( final List< T > items ) {
        this( DEFAULT_LABEL, items );
    }

    public Group( final String pLabel,
                  final List< T > pItems ) {
        label = pLabel;
        items = pItems;

        for ( final T item : items ) {
            item.setGroup( this );
        }
    }

    @SafeVarargs
    public final void addAll( final T... items ) {
        for ( final T item : items ) {
            addItem( item );
        }
    }

    @SafeVarargs
    public final void removeAll( final T... items ) {
        for ( final T item : items ) {
            removeItem( item );
        }
    }

    public void addItem( final T pItem ) {
        try {
            checkItem( pItem );
        } catch( final Exception e ) {
            LOGGER.log( Level.ERROR, e.getMessage(), e );
        }

        if ( pItem.getGroup() != null ) {
            pItem.getGroup().removeItem( pItem );
        }

        items.add( pItem );
        pItem.setGroup( this );
    }

    public void removeItem( final T pItem ) {
        try {
            checkItem( pItem );
            checkGroup( pItem );
        } catch( final Exception e ) {
            LOGGER.log( Level.ERROR, e.getMessage(), e );
        }

        items.remove( pItem );
        pItem.setGroup( null );
    }

    public void moveItem( final int idx,
                          final T pItem ) {
        try {
            checkItem( pItem );
            checkGroup( pItem );
        } catch( final Exception e ) {
            LOGGER.log( Level.ERROR, e.getMessage(), e );
        }

        if( ( idx < 0 ) || ( idx >= items.size() ) ) {
            throw new IndexOutOfBoundsException();
        }

        items.remove( pItem );
        items.add( idx, pItem );
    }

    protected void checkItem( final T pItem )
            throws IllegalArgumentException {
        if ( pItem == null ) {
            throw new IllegalArgumentException( "Item is null" );
        }
    }

    protected void checkGroup( final T pItem )
            throws IllegalStateException{
        if ( !Objects.equals( pItem.getGroup(), this ) ) {
            throw new IllegalArgumentException( "Item is not in this group" );
        }
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel( final String pLabel ) {
        label = pLabel;
    }

    @Override
    public int hashCode() {
        return Objects.hash( label, items );
    }

    @Override
    public boolean equals( final Object obj ) {
        return ( obj instanceof Group< ? extends GroupItem< T > > group )
                && Objects.equals( group.label, label )
                && Objects.equals( group.items, items );
    }

    public List< T > getItems() {
        return items;
    }
}
