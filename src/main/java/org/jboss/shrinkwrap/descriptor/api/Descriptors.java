/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.shrinkwrap.descriptor.api;

import org.jboss.shrinkwrap.descriptor.spec.beans.BeansDef;
import org.jboss.shrinkwrap.descriptor.spec.persistence.PersistenceDef;
import org.jboss.shrinkwrap.descriptor.spec.web.WebAppDef;

/**
 * @author Dan Allen
 */
public class Descriptors
{
   // FIXME total hack...we need to do what ShrinkWrap does w/ archives
   public static <T extends DescriptorDef<?>> T create(Class<T> type)
   {
      if (type.equals(PersistenceDef.class))
      {
         return (T) new PersistenceDef();
      }
      else if (type.equals(BeansDef.class))
      {
         return (T) new BeansDef();
      }
      else if (type.equals(WebAppDef.class))
      {
         return (T) new WebAppDef();
      }
      return null;
   }
}
