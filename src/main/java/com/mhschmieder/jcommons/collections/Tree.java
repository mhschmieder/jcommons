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
import java.util.List;
import java.util.Objects;

public class Tree< T extends Item< T > > implements Item< Tree< T > > {

    private static final String DEFAULT_TREE_LABEL = "New Tree";

    private String label;

    private Node< T > root;

    public Tree() {
        this( DEFAULT_TREE_LABEL );
    }

    public Tree( String pLabel ) {
        this( pLabel,null );
    }

    public Tree( String pLabel, T rootValue ) {
        label = pLabel;
        root = new Node<>( "Root", rootValue );
    }

    @Override
    public int hashCode() {
        return Objects.hash( label, root );
    }

    @Override
    public boolean equals( final Object obj ) {
        return ( obj instanceof Tree< ? extends Item< T > > tree )
                && Objects.equals( label, tree.label )
                && Objects.equals( root, tree.root );
    }

    public List< Node< T > > getRootChildren() {
        return root.getChildren();
    }

    public List< T > getAllValues() {
        final List< T > list = new ArrayList<>();
        collectValues( root, list );
        return list;
    }

    private void collectValues( Node< T > node, List< T > list ) {
        if ( node == null ) {
            return;
        }
        if ( node.getValue() != null ) {
            list.add( node.getValue() );
        }
        if ( node.getChildren() == null ) {
            return;
        }
        for ( Node< T > child : node.getChildren() ) {
            collectValues( child, list );
        }
    }

    public List< Node< T > > getAllNodes() {
        final List< Node< T > > list = new ArrayList<>();
        getNodes( list, root );
        return list;
    }

    protected void getNodes( List< Node< T > > list, Node< T > pNode ) {
        list.add( pNode );
        if ( pNode.isLeaf() ){
            return;
        }
        for ( final Node< T > child : pNode.getChildren() ) {
            if( !child.isLeaf() ) {
                getNodes( list, child );
            }
            list.add( child );
        }
    }

    public Node< T > getNode( final String pLabel ) {
        final List< Node< T > > list = getAllNodes();
        for ( final Node<T> node : list ) {
            if ( Objects.equals( node.getLabel(), pLabel ) ){
                return node;
            }
        }
        return null;
    }

    public void sort() {
        sort( root );
    }

    protected void sort( final Node< T > pNode ) {
        pNode.sort();
        for ( final Node< T > child : pNode.getChildren() ) {
            sort( child );
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

    public Node< T > getRoot() {
        return root;
    }

    public void setRoot( final Node< T > root ) {
        this.root = root;
    }
}
