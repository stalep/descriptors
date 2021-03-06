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
package org.jboss.shrinkwrap.descriptor.api.spec.cdi.beans;

import java.lang.annotation.Annotation;

import org.jboss.shrinkwrap.descriptor.api.Descriptor;

/**
 * DSL Grammar to construct / alter CDI Beans
 * XML Descriptors
 * 
 * @author <a href="mailto:andrew.rubinger@jboss.org">ALR</a>
 */
public interface BeansDescriptor extends Descriptor
{

   BeansDescriptor interceptors(Class<?>... classes);

   BeansDescriptor interceptor(Class<?> clazz);

   BeansDescriptor decorators(Class<?>... classes);

   BeansDescriptor decorator(Class<?> clazz);

   BeansDescriptor alternativeClasses(Class<?>... classes);

   BeansDescriptor alternativeClass(Class<?> clazz);

   BeansDescriptor alternativeStereotypes(Class<? extends Annotation>... annotations);

   BeansDescriptor alternativeStereotype(Class<? extends Annotation> clazz);

}