package br.com.torres.ddlGenerator.enums;

import lombok.Getter;

@Getter
public enum Queries {

	CREATE_TABLE("CREATE TABLE #DADOS_TABELA(COLUMNTYPE VARCHAR(MAX), TABLENAME VARCHAR(MAX), COLUMNNAME VARCHAR(MAX), IS_PK int)\r\n" + 
			"CREATE TABLE #TEMP_CREATE_INSERT(COLUMNTYPE VARCHAR(MAX), TABLENAME VARCHAR(MAX), COLUMNNAME VARCHAR(MAX), IS_PK int)\r\n" + 
			"INSERT INTO #DADOS_TABELA\r\n" + 
			"SELECT UPPER(CONCAT(C.NAME , ' ' , CASE\r\n" + 
			"                                       WHEN TY.NAME = 'VARCHAR' THEN 'VARCHAR('+IIF(CONVERT(VARCHAR,C.MAX_LENGTH) = '-1', 'MAX', CONVERT(VARCHAR,C.MAX_LENGTH))+')'\r\n" + 
			"                                       WHEN TY.NAME = 'NUMERIC' THEN 'NUMERIC('+CONVERT(VARCHAR,C.PRECISION)+','+CONVERT(VARCHAR,C.SCALE)+')'\r\n" + 
			"                                       ELSE TY.NAME\r\n" + 
			"                                   END , ' NOT NULL')) AS COLUMNTYPE,\r\n" + 
			"       OBJECT_NAME(KC.PARENT_OBJECT_ID) AS TABLENAME,\r\n" + 
			"       C.NAME COLUMNNAME,\r\n" + 
			"       1 AS IS_PK\r\n" + 
			"FROM SYS.KEY_CONSTRAINTS KC\r\n" + 
			"INNER JOIN SYS.INDEX_COLUMNS IC ON KC.PARENT_OBJECT_ID = IC.OBJECT_ID\r\n" + 
			"AND KC.UNIQUE_INDEX_ID = IC.INDEX_ID\r\n" + 
			"INNER JOIN SYS.COLUMNS C ON IC.OBJECT_ID = C.OBJECT_ID\r\n" + 
			"AND IC.COLUMN_ID = C.COLUMN_ID\r\n" + 
			"JOIN SYS.TYPES TY ON C.USER_TYPE_ID = TY.USER_TYPE_ID\r\n" + 
			"FULL JOIN SYS.OBJECTS O ON O.OBJECT_ID = C.DEFAULT_OBJECT_ID\r\n" + 
			"FULL JOIN SYS.DEFAULT_CONSTRAINTS DC ON DC.OBJECT_ID = O.OBJECT_ID\r\n" + 
			"WHERE KC.TYPE_DESC = 'PRIMARY_KEY_CONSTRAINT'\r\n" + 
			"UNION ALL\r\n" + 
			"SELECT UPPER(CONCAT(C.NAME , ' ' , CASE\r\n" + 
			"                                       WHEN TY.NAME = 'VARCHAR' THEN 'VARCHAR('+IIF(CONVERT(VARCHAR,C.MAX_LENGTH) = '-1', 'MAX', CONVERT(VARCHAR,C.MAX_LENGTH))+')'\r\n" + 
			"                                       WHEN TY.NAME = 'NUMERIC' THEN 'NUMERIC('+CONVERT(VARCHAR,C.PRECISION)+','+CONVERT(VARCHAR,C.SCALE)+')'\r\n" + 
			"                                       ELSE TY.NAME\r\n" + 
			"                                   END , ' NOT NULL')) AS COLUMNTYPE,\r\n" + 
			"       t.name AS TABLENAME,\r\n" + 
			"       C.NAME COLUMNNAME,\r\n" + 
			"       0 AS IS_PK\r\n" + 
			"FROM SYS.TABLES T\r\n" + 
			"INNER JOIN SYS.COLUMNS C ON T.OBJECT_ID = C.OBJECT_ID\r\n" + 
			"JOIN SYS.TYPES TY ON C.USER_TYPE_ID = TY.USER_TYPE_ID\r\n" + 
			"FULL JOIN SYS.OBJECTS O ON O.OBJECT_ID = C.DEFAULT_OBJECT_ID\r\n" + 
			"FULL JOIN SYS.DEFAULT_CONSTRAINTS DC ON DC.OBJECT_ID = O.OBJECT_ID\r\n" + 
			"LEFT JOIN #DADOS_TABELA D ON T.NAME = D.TABLENAME\r\n" + 
			"WHERE D.TABLENAME IS NULL\r\n" + 
			"  AND T.NAME IS NOT NULL\r\n" + 
			"  AND C.column_id=1\r\n" + 
			"  INSERT INTO #TEMP_CREATE_INSERT(TABLENAME, COLUMNNAME, COLUMNTYPE, IS_PK)\r\n" + 
			"SELECT P.TABLENAME ,\r\n" + 
			"       STUFF(\r\n" + 
			"               (SELECT ',' + C.COLUMNNAME\r\n" + 
			"                FROM #DADOS_TABELA AS C\r\n" + 
			"                WHERE C.TABLENAME = P.TABLENAME\r\n" + 
			"                GROUP BY C.TABLENAME, C.COLUMNNAME\r\n" + 
			"                FOR XML PATH('')), 1, LEN(','), '') AS COLUMNNAME ,\r\n" + 
			"       STUFF(\r\n" + 
			"               (SELECT ',' + C.COLUMNTYPE\r\n" + 
			"                FROM #DADOS_TABELA AS C\r\n" + 
			"                WHERE C.TABLENAME = P.TABLENAME\r\n" + 
			"                GROUP BY C.TABLENAME, C.COLUMNTYPE\r\n" + 
			"                FOR XML PATH('')), 1, LEN(','), '') AS COLUMNTYPE,\r\n" + 
			"       IS_PK\r\n" + 
			"FROM #DADOS_TABELA AS P\r\n" + 
			"GROUP BY P.TABLENAME,\r\n" + 
			"         P.COLUMNTYPE,\r\n" + 
			"         IS_PK\r\n" + 
			"DROP TABLE #DADOS_TABELA\r\n" + 
			"SELECT TABLENAME,\r\n" + 
			"'IF NOT EXISTS (SELECT TOP 1 1 FROM SYS.OBJECTS WHERE OBJECT_ID = OBJECT_ID(N'''+TABLENAME+''') AND TYPE IN (N''U''))\nBEGIN\n '+ CONCAT('	CREATE TABLE ', TABLENAME, ' (', COLUMNTYPE, IIF(MAX(IS_PK)=1, ' PRIMARY KEY(' + COLUMNNAME + '))', ')')) +' \nEND\n\n' AS SCRIPT\r\n" + 
			"FROM #TEMP_CREATE_INSERT\r\n" + 
			"GROUP BY TABLENAME,\r\n" + 
			"         COLUMNNAME,\r\n" + 
			"         COLUMNTYPE\r\n" + 
			"DROP TABLE #TEMP_CREATE_INSERT"),
	
	CREATE_COLUMNS("SELECT NAME INTO #TABLE_NAME\r\n" + 
			"FROM sys.tables T\r\n" + 
			"ORDER BY NAME\r\n" + 
			"SELECT t.name TABLENAME,\r\n" + 
			"       'IF NOT EXISTS (SELECT TOP 1 1 FROM SYS.COLUMNS C JOIN SYS.TABLES T ON C.OBJECT_ID = T.OBJECT_ID WHERE T.NAME = ' +''''+T.NAME+''''+' AND C.NAME = '+''''+C.NAME+''') ' +'\nBEGIN ' + '\n      ALTER TABLE '+T.NAME+' ADD '+ C.NAME + ' ' + CASE\r\n" + 
			"                                                                                                                                                                                                                                             WHEN TY.NAME = 'VARCHAR' THEN 'VARCHAR('+IIF(CONVERT(VARCHAR,C.MAX_LENGTH) = '-1', 'MAX', CONVERT(VARCHAR,C.MAX_LENGTH))+')'\r\n" + 
			"                                                                                                                                                                                                                                             WHEN TY.NAME = 'NUMERIC' THEN 'NUMERIC('+CONVERT(VARCHAR,C.PRECISION)+','+CONVERT(VARCHAR,C.SCALE)+')'\r\n" + 
			"                                                                                                                                                                                                                                             ELSE TY.NAME\r\n" + 
			"                                                                                                                                                                                                                                         END + CASE\r\n" + 
			"                                                                                                                                                                                                                                                   WHEN C.is_nullable = 0\r\n" + 
			"                                                                                                                                                                                                                                                        AND C.default_object_id > 0 THEN ' NOT NULL DEFAULT '+DC.definition\r\n" + 
			"                                                                                                                                                                                                                                                   ELSE ''\r\n" + 
			"                                                                                                                                                                                                                                               END +' \nEND\n\n' AS SCRIPT\r\n" + 
			"FROM SYS.COLUMNS C\r\n" + 
			"JOIN SYS.TABLES T ON C.OBJECT_ID = T.OBJECT_ID\r\n" + 
			"JOIN SYS.TYPES TY ON C.USER_TYPE_ID = TY.USER_TYPE_ID\r\n" + 
			"FULL JOIN sys.objects O ON O.object_id = C.default_object_id\r\n" + 
			"FULL JOIN sys.default_constraints DC ON DC.object_id = O.object_id\r\n" + 
			"WHERE T.NAME in\r\n" + 
			"    (SELECT name\r\n" + 
			"     FROM #TABLE_NAME)\r\n" + 
			"ORDER BY T.NAME\r\n" + 
			"DROP TABLE #TABLE_NAME"),
	
			CREATE_DEFAULT("SELECT\r\n" + 
					"t.name TABLENAME,'IF NOT EXISTS '\r\n" + 
					"+'(SELECT TOP 1 1 '\r\n" + 
					"+'FROM SYS.COLUMNS C '\r\n" + 
					"+'JOIN SYS.TABLES T ON C.OBJECT_ID = T.OBJECT_ID '\r\n" + 
					"+'INNER JOIN sys.objects O ON O.object_id = C.default_object_id '\r\n" + 
					"+'WHERE T.name = '+''''+T.name+''''+' AND C.name = '+''''+C.name+''''+') '\r\n" + 
					"+'\nBEGIN ' \r\n" + 
					"+'\n     ALTER TABLE '+T.name+' ADD  DEFAULT '+DC.definition+' FOR '+C.name\r\n" + 
					"+'\nEND\n\n' as SCRIPT\r\n" + 
					"FROM SYS.COLUMNS C\r\n" + 
					"JOIN SYS.TABLES T ON C.OBJECT_ID = T.OBJECT_ID\r\n" + 
					"JOIN SYS.TYPES TY ON C.USER_TYPE_ID = TY.USER_TYPE_ID\r\n" + 
					"INNER JOIN sys.objects O ON O.object_id = C.default_object_id\r\n" + 
					"INNER JOIN sys.default_constraints DC ON DC.object_id = O.object_id\r\n" + 
					""),
			
			CREATE_FK("SELECT\r\n" + 
					"FK_MAIN_TABLE.name TABLENAME,'IF NOT EXISTS '\r\n" + 
					"+'(SELECT TOP 1 1 '\r\n" + 
					"+'FROM SYS.FOREIGN_KEYS WHERE NAME = '+''''+FK_TABLE.name+''''+')'\r\n" + 
					"+'\nBEGIN ' \r\n" + 
					"+'\n     ALTER TABLE '+FK_MAIN_TABLE.name+' WITH CHECK ADD  CONSTRAINT '+FK_TABLE.NAME+' FOREIGN KEY('+FK_MAIN_TABLE_COLUMN.name+') REFERENCES  '+FK_REFERENCE_TABLE.NAME+' ('+FK_REFERENCE_TABLE_COLUMN.name+')'\r\n" + 
					"+'\nEND\n\n'as SCRIPT\r\n" + 
					"FROM sys.foreign_keys FK_TABLE\r\n" + 
					"JOIN sys.tables FK_MAIN_TABLE ON FK_MAIN_TABLE.object_id = FK_TABLE.parent_object_id\r\n" + 
					"JOIN sys.tables FK_REFERENCE_TABLE ON FK_REFERENCE_TABLE.object_id = FK_TABLE.referenced_object_id\r\n" + 
					"JOIN sys.foreign_key_columns FK_COLUMNS on FK_COLUMNS.constraint_object_id = FK_TABLE.object_id\r\n" + 
					"JOIN sys.columns FK_MAIN_TABLE_COLUMN on (FK_MAIN_TABLE_COLUMN.object_id = FK_COLUMNS.parent_object_id and FK_MAIN_TABLE_COLUMN.column_id = FK_COLUMNS.parent_column_id)\r\n" + 
					"JOIN sys.columns FK_REFERENCE_TABLE_COLUMN on (FK_REFERENCE_TABLE_COLUMN.object_id = FK_COLUMNS.referenced_object_id and FK_REFERENCE_TABLE_COLUMN.column_id = FK_COLUMNS.referenced_column_id)\r\n" + 
					"")
	;
	
	private String query;
	
	Queries(String query){
		this.query = query;
	}
	
}
