package com.confighub;


import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import picocli.CommandLine;

import java.sql.Connection;
import java.sql.DriverManager;


@CommandLine.Command( name = "example",
                      mixinStandardHelpOptions = true,
                      version = "Picocli example 3.0" )
public class DatabaseMigration
      implements Runnable
{
    public static void main( String[] args )
    {
        CommandLine.run( new DatabaseMigration(), System.out, args );
    }


    enum DB_TYPE
    {
        oracle,
        postgresql,
        mysql
    }

    @CommandLine.Option( names = { "-t",
                                   "--databaseType" },
                         required = true,
                         description = "Database Type" )
    private DB_TYPE databaseType;

    @CommandLine.Option( names = { "-r",
                                   "--url" },
                         required = true,
                         description = "Database URL.  Ex: jdbc:postgresql://127.0.0.1:5432/ConfigHub" )
    private String databaseUrl;

    @CommandLine.Option( names = { "-u",
                                   "--user" },
                         required = true,
                         description = "Database username" )
    private String username;

    @CommandLine.Option( names = { "-p",
                                   "--password" },
                         required = true,
                         description = "Database password" )
    private String password;


    public void run()
    {
        updateDatabase();
    }


    private void updateDatabase()
    {
        final Connection con;
        JdbcConnection jdbcCon = null;
        try
        {
            switch ( databaseType )
            {
                case oracle:
                    Class.forName( "oracle.jdbc.driver.OracleDriver" );
                    break;

                case postgresql:
                    Class.forName( "org.postgresql.Driver" );
                    break;

                case mysql:
                    Class.forName( "com.mysql.jdbc.Driver" );
                    break;
            }

            con = DriverManager.getConnection( databaseUrl, username, password );
            jdbcCon = new JdbcConnection( con );

            // Initialize Liquibase and run the update
            final Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation( jdbcCon );

            final Liquibase liquibase = new Liquibase( "db/changelog-master.yml",
                                                       new ClassLoaderResourceAccessor(),
                                                       database );
            liquibase.update( "" );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jdbcCon != null )
            {
                try
                {
                    jdbcCon.rollback();
                    jdbcCon.close();
                }
                catch ( final Exception e )
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
