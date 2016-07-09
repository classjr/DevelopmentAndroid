package com.manager.br.aplications.DataBase.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by junior on 08/07/2016.
 */
@DatabaseTable(tableName = "phone")
public class Phone{
  @DatabaseField(generatedId = true)
  private Integer id;
  
  @DatabaseField(columnName = "number")
  private String number; 
  
  @DatabaseField(columnName = "type")
  private String type;
  
  @DatabaseField (foreign = true, foreignAutoRefresh = true, columnName = "person_id")
  private Person person;
  
  
}
