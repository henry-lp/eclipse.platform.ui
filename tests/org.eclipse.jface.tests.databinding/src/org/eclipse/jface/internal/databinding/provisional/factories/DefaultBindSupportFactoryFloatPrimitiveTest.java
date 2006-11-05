/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Brad Reynolds - bug 116920
 ******************************************************************************/

package org.eclipse.jface.internal.databinding.provisional.factories;

import junit.framework.TestCase;

import org.eclipse.jface.databinding.DataBindingContext;
import org.eclipse.jface.databinding.beans.BeansObservables;
import org.eclipse.jface.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.examples.databinding.ModelObject;
import org.eclipse.swt.widgets.Display;

public class DefaultBindSupportFactoryFloatPrimitiveTest extends TestCase {
    private DataBindingContext ctx;

    private TestDataObject dataObject;

    public void setUp() {
        Realm.setDefault(SWTObservables.getRealm(Display.getDefault()));

        ctx = new DataBindingContext();
        dataObject = new TestDataObject();
        dataObject.setStringVal("0");
        dataObject.setFloatPrimitiveVal(0);
        dataObject.setFloatVal(new Float(0));
    }

    public void testStringToFloatPrimitiveConverter() {
        ctx.bindValue(BeansObservables.getAttribute(dataObject, "stringVal"), BeansObservables.getAttribute(dataObject,
                "floatPrimitiveVal"), null);

        dataObject.setFloatPrimitiveVal((float) 789.5);
        assertEquals("float value does not match", 789.5, dataObject.getFloatPrimitiveVal(), .001);
        assertEquals("String value does not match", "789.5", dataObject.getStringVal());
        assertNull("No errors should be found.", ctx.getValidationError().getValue());

        dataObject.setStringVal("910.5");
        assertEquals("float value does not match", 910.5, dataObject.getFloatPrimitiveVal(), .001);
        assertEquals("String value does not match", "910.5", dataObject.getStringVal());
        assertNull("No errors should be found.", ctx.getValidationError().getValue());

        dataObject.setStringVal("");
        assertEquals("float value does not match", 910.5, dataObject.getFloatPrimitiveVal(), .001);
        assertEquals("String value does not match", "", dataObject.getStringVal());
        assertNotNull("Errors should be found.", ctx.getValidationError().getValue());

        dataObject.setStringVal(null);
        assertEquals("float value does not match", 910.5, dataObject.getFloatPrimitiveVal(), .001);
        assertNull("String value does not match", dataObject.getStringVal());
        assertNotNull("Errors should be found.", ctx.getValidationError().getValue());
    }

    public void testFloatToFloatPrimitiveConverter() {
        ctx.bindValue(BeansObservables.getAttribute(dataObject, "floatVal"), BeansObservables.getAttribute(dataObject,
                "floatPrimitiveVal"), null);

        dataObject.setFloatPrimitiveVal((float) 789.5);
        assertEquals("float value does not match", 789.5, dataObject.getFloatPrimitiveVal(), .001);
        assertEquals("Float value does not match", new Float(789.5), dataObject.getFloatVal());
        assertNull("No errors should be found.", ctx.getValidationError().getValue());

        dataObject.setFloatVal(new Float(910.5));
        assertEquals("float value does not match", 910.5, dataObject.getFloatPrimitiveVal(), .001);
        assertEquals("Float value does not match", new Float(910.5), dataObject.getFloatVal());
        assertNull("No errors should be found.", ctx.getValidationError().getValue());

        dataObject.setFloatVal(null);
        assertEquals("float value does not match", 910.5, dataObject.getFloatPrimitiveVal(), .001);
        assertNull("Float value does not match", dataObject.getFloatVal());
        assertNotNull("Errors should be found.", ctx.getValidationError().getValue());
    }

    public void testObjectToFloatPrimitiveConverter() {
        ctx.bindValue(BeansObservables.getAttribute(dataObject, "objectVal"), BeansObservables.getAttribute(dataObject,
                "floatPrimitiveVal"), null);

        dataObject.setFloatPrimitiveVal((float) 789.5);
        assertEquals("float value does not match", 789.5, dataObject.getFloatPrimitiveVal(), .001);
        assertEquals("Object value does not match", new Float(789.5), dataObject.getObjectVal());
        assertNull("No errors should be found.", ctx.getValidationError().getValue());

        dataObject.setObjectVal(new Float(910.5));
        assertEquals("float value does not match", 910.5, dataObject.getFloatPrimitiveVal(), .001);
        assertEquals("Object value does not match", new Float(910.5), dataObject.getObjectVal());
        assertNull("No errors should be found.", ctx.getValidationError().getValue());

        dataObject.setObjectVal(null);
        assertEquals("float value does not match", 910.5, dataObject.getFloatPrimitiveVal(), .001);
        assertNull("Object value does not match", dataObject.getObjectVal());
        assertNotNull("Errors should be found.", ctx.getValidationError().getValue());

        Object object = new Object();
        dataObject.setObjectVal(object);
        assertEquals("float value does not match", 910.5, dataObject.getFloatPrimitiveVal(), .001);
        assertSame("Object value does not match", object, dataObject.getObjectVal());
        assertNotNull("Errors should be found.", ctx.getValidationError().getValue());
    }

    public class TestDataObject extends ModelObject {
        private float floatPrimitiveValue;

        private String stringVal;

        private Float floatVal;

        private Object objectVal;

        public Float getFloatVal() {
            return floatVal;
        }

        public void setFloatVal(Float floatVal) {
            Object oldVal = this.floatVal;
            this.floatVal = floatVal;
            firePropertyChange("floatVal", oldVal, this.floatVal);
        }

        public float getFloatPrimitiveVal() {
            return floatPrimitiveValue;
        }

        public void setFloatPrimitiveVal(float floatPrimitiveValue) {
            float oldVal = this.floatPrimitiveValue;
            this.floatPrimitiveValue = floatPrimitiveValue;
            firePropertyChange("floatPrimitiveVal", new Float(oldVal), new Float(this.floatPrimitiveValue));
        }

        public String getStringVal() {
            return stringVal;
        }

        public void setStringVal(String stringVal) {
            Object oldVal = this.stringVal;
            this.stringVal = stringVal;
            firePropertyChange("stringVal", oldVal, this.stringVal);
        }

        public Object getObjectVal() {
            return objectVal;
        }

        public void setObjectVal(Object objectVal) {
            Object oldVal = this.objectVal;
            this.objectVal = objectVal;
            firePropertyChange("objectVal", oldVal, this.objectVal);
        }
    }
}