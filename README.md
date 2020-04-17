# prestosql-custom-function

Steps To add custom function to prestosql(prestosql.io)

1. Build the project using maven clean install

2. Jar will be cretaed in target directory(ending with .0.0.1-SNAPSHOT-jar-with-dependencies.jar)

3. create a seprate directory in presto server plugin directory udf-function

4.Copy the jar which get created in step 2

5.restart your prestosqlserver.

6.select maskcard('encryptedcardnumber','key');

7.Output 123456XXXXXX1234

8.If you want to add your custom function follow the link https://prestosql.io/docs/current/develop/functions.html 
