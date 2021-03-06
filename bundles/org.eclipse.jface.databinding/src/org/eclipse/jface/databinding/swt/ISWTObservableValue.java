/*******************************************************************************
 * Copyright (c) 2006, 2015 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jface.databinding.swt;

import org.eclipse.core.databinding.observable.value.IObservableValue;

/**
 * {@link IObservableValue} observing an SWT widget.
 *
 * @param <T>
 *            type of the value of the observable
 *
 * @since 1.1
 */
public interface ISWTObservableValue<T> extends ISWTObservable, IObservableValue<T> {

}
