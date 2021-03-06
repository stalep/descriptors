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
package org.jboss.shrinkwrap.descriptor.impl.spec.jpa.persistence;

import org.jboss.shrinkwrap.descriptor.api.spec.jpa.persistence.PersistenceUnitDef;
import org.jboss.shrinkwrap.descriptor.api.spec.jpa.persistence.ProviderType;
import org.jboss.shrinkwrap.descriptor.api.spec.jpa.persistence.SchemaGenerationModeType;
import org.jboss.shrinkwrap.descriptor.api.spec.jpa.persistence.SharedCacheModeType;
import org.jboss.shrinkwrap.descriptor.api.spec.jpa.persistence.TransactionType;
import org.jboss.shrinkwrap.descriptor.api.spec.jpa.persistence.ValidationModeType;

/**
 * @author Dan Allen
 */
public class PersistenceUnitDefImpl extends PersistenceDescriptorImpl implements PersistenceUnitDef
{
   private PersistenceUnit persistenceUnit;
   
   public PersistenceUnitDefImpl(PersistenceModel persistence, PersistenceUnit persistenceUnit)
   {
      super(persistence);
      this.persistenceUnit = persistenceUnit;
   }
   
   @Override
   public PersistenceUnitDef name(String name)
   {
      persistenceUnit.setName(name);
      return this;
   }
   
   @Override
   public PersistenceUnitDef description(String description)
   {
      persistenceUnit.setDescription(description);
      return this;
   }
   
   @Override
   public PersistenceUnitDef nonJtaDataSource(String jndiName)
   {
      persistenceUnit.setNonJtaDataSource(jndiName);
      return this;
   }
   
   @Override
   public PersistenceUnitDef transactionType(TransactionType transactionType)
   {
      persistenceUnit.setTransactionType(transactionType);
      return this;
   }
   
   @Override
   public PersistenceUnitDef jtaDataSource(String jndiName)
   {
      persistenceUnit.setJtaDataSource(jndiName);
      return this;
   }
   
   @Override
   public PersistenceUnitDef property(String name, Object value)
   {
      persistenceUnit.getProperties().add(new Property(name, String.valueOf(value)));
      return this;
   }
   
   @Override
   public PersistenceUnitDef classes(Class<?>... classes)
   {
      for (Class<?> c : classes)
      {
         persistenceUnit.getClasses().add(c.getName());
      }
      return this;
   }
   
   @Override
   public PersistenceUnitDef jarFiles(String... paths)
   {
      for (String p : paths)
      {
         persistenceUnit.getJarFiles().add(p);
      }
      return this;
   }
   
   @Override
   public PersistenceUnitDef jarFile(String path)
   {
      return jarFiles(path);
   }
   
   @Override
   public PersistenceUnitDef mappingFiles(String... paths)
   {
      for (String p : paths)
      {
         persistenceUnit.getMappingFiles().add(p);
      }
      return this;
   }

   @Override
   public PersistenceUnitDef mappingFile(String path)
   {
      return mappingFiles(path);
   }
   
   @Override
   public PersistenceUnitDef sharedCacheMode(SharedCacheModeType sharedCacheMode)
   {
      persistenceUnit.setSharedCacheMode(sharedCacheMode);
      return this;
   }
   
   @Override
   public PersistenceUnitDef validationMode(ValidationModeType validationMode)
   {
      persistenceUnit.setValidationMode(validationMode);
      return this;
   }
   
   @Override
   public PersistenceUnitDef excludeUnlistedClasses()
   {
      persistenceUnit.setExcludeUnlistedClasses(true);
      return this;
   }
   
   @Override
   public PersistenceUnitDef includeUnlistedClasses()
   {
      persistenceUnit.setExcludeUnlistedClasses(false);
      return this;
   }
   
   @Override
   public PersistenceUnitDef provider(String provider)
   {
      persistenceUnit.setProvider(provider);
      return this;
   }
   
   @Override
   public PersistenceUnitDef provider(ProviderType providerType)
   {
      persistenceUnit.setProvider(providerType.getProviderClass());
      return this;
   }
   
   @Override
   public PersistenceUnitDef showSql()
   {
      ProviderType providerType = ProviderType.fromProviderClass(persistenceUnit.getProvider());
      if (providerType == null || providerType == ProviderType.HIBERNATE)
      {
         persistenceUnit.getProperties().add(new Property("hibernate.show_sql", "true"));
      }
      if (providerType == null || providerType == ProviderType.ECLIPSE_LINK)
      {
         persistenceUnit.getProperties().add(new Property("eclipselink.logging.level", "FINE"));
      }
      return this;
   }
   
   @Override
   public PersistenceUnitDef formatSql()
   {
      ProviderType providerType = ProviderType.fromProviderClass(persistenceUnit.getProvider());
      if (providerType == null || providerType == ProviderType.HIBERNATE)
      {
         persistenceUnit.getProperties().add(new Property("hibernate.format_sql", "true"));
      }
      return this;
   }
   
   @Override
   public PersistenceUnitDef schemaGenerationMode(SchemaGenerationModeType schemaGenerationMode)
   {
      ProviderType providerType = ProviderType.fromProviderClass(persistenceUnit.getProvider());
      if (providerType == null || providerType == ProviderType.HIBERNATE)
      {
         String value = null;
         if (SchemaGenerationModeType.CREATE.equals(schemaGenerationMode))
         {
            value = "create";
         }
         else if (SchemaGenerationModeType.CREATE_DROP.equals(schemaGenerationMode))
         {
            value = "create-drop";
         }
         else if (SchemaGenerationModeType.UPDATE.equals(schemaGenerationMode))
         {
            value = "update";
         }
         else if (SchemaGenerationModeType.NONE.equals(schemaGenerationMode))
         {
            value = "none";
         }
         if (value != null)
         {
            persistenceUnit.getProperties().add(new Property("hibernate.hbm2ddl.auto", value));
         }
      }
      if (providerType == null || providerType == ProviderType.ECLIPSE_LINK)
      {
         String value = null;
         if (SchemaGenerationModeType.CREATE.equals(schemaGenerationMode))
         {
            value = "create-tables";
         }
         else if (SchemaGenerationModeType.CREATE_DROP.equals(schemaGenerationMode))
         {
            value = "drop-and-create-tables";
         }
         else if (SchemaGenerationModeType.UPDATE.equals(schemaGenerationMode))
         {
            throw new UnsupportedOperationException(SchemaGenerationModeType.UPDATE + " not supported by provider " + ProviderType.ECLIPSE_LINK);
         }
         else if (SchemaGenerationModeType.NONE.equals(schemaGenerationMode))
         {
            value = "none";
         }
         if (value != null)
         {
            persistenceUnit.getProperties().add(new Property("eclipselink.ddl-generation", value));
         }
      }
      return this;
   }
}
