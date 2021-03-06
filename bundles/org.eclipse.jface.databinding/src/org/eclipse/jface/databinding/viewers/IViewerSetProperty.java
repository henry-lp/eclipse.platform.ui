/*******************************************************************************
 * Copyright (c) 2009, 2015 Matthew Hall and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 264286)
 *******************************************************************************/

package org.eclipse.jface.databinding.viewers;

import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.property.set.ISetProperty;
import org.eclipse.jface.viewers.Viewer;

/**
 * {@link ISetProperty} for observing a JFace viewer
 *
 * @param <S>
 *            type of the source object
 * @param <E>
 *            type of the elements in the set
 *
 * @since 1.3
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IViewerSetProperty<S, E> extends ISetProperty<S, E> {
	/**
	 * Returns an {@link IViewerObservableSet} observing this set property on
	 * the given viewer
	 *
	 * @param viewer
	 *            the source viewer
	 * @return an observable set observing this set property on the given viewer
	 */
	public IViewerObservableSet<E> observe(Viewer viewer);

	/**
	 * This method is redeclared to trigger ambiguous method errors that are hidden
	 * by a suspected Eclipse compiler bug 536911. By triggering the bug in this way
	 * clients avoid a change of behavior when the bug is fixed. When the bug is
	 * fixed this redeclaration should be removed.
	 */
	@Override
	public IObservableSet<E> observe(S viewer);
}
