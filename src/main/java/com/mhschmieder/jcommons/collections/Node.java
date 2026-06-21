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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Node< T extends Item< T > > implements Item< Node< T > > {

    private static final String DEFAULT_NODE_LABEL
            = "New Node";

    private String label;

    private T value;

    private Node< T > parent;

    private List< Node< T > > children;

    public Node() {
        this( DEFAULT_NODE_LABEL );
    }

    public Node( final String pLabel ) {
        this( pLabel, null );
    }

    public Node( final T pValue ) {
        this( pValue.getLabel(), pValue );
    }

    public Node( final String pLabel,
                 final T pValue ) {
        label = pLabel;
        value = pValue;
        parent = null;
        children = new ArrayList<>();
    }

    public void addChild( final Node< T > pChild ) {
        checkChild( pChild );
        children.add( pChild );
        pChild.setParent( this );
    }

    public void clearChildren() {
        final List< Node< T > > oldChildren = new ArrayList<>( children );
        for( final Node< T > child : oldChildren ) {
            removeChild( child );
        }
    }

    public void removeChild( final Node< T > pChild ) {
        checkChild( pChild );
        checkParent( pChild );
        children.remove( pChild );
    }

    public void checkChild( final Node< T > pChild )
            throws IllegalArgumentException, NoSuchElementException{
        if ( pChild == null ) {
            throw new IllegalArgumentException( "Child is null" );
        }
    }

    public void checkParent( final Node< T > pChild ) {
        if ( !children.contains( pChild )
                || !Objects.equals( pChild.getParent(), this ) ) {
            throw new NoSuchElementException( "Child is not found" );
        }
    }

    public void sort() {
        Collections.sort( children );
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
    public boolean equals( final Object obj ) {
        return ( obj instanceof Node< ? extends Item< T > > node )
                && Objects.equals( value, node.value )
                && Objects.equals( label, node.label );
    }

    @Override
    public int hashCode() {
        return Objects.hash( value, label );
    }

    public T getValue() {
        return value;
    }

    public void setValue( final T pValue ) {
        value = pValue;
    }

    public Node< T > getParent() {
        return parent;
    }

    public void setParent( final Node< T > pParent ) {
        if ( ( parent != null ) && !Objects.equals(
                parent.getValue(), pParent.getValue() ) ) {
            parent.removeChild( this );
        }
        parent = pParent;
    }

    public List< Node< T > > getChildren() {
        return Collections.unmodifiableList( children );
    }

    public void setChildren( final List< Node< T > > pChildren ) {
        clearChildren();
        for ( final Node< T > child : pChildren ) {
            addChild( child );
        }
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
}
