CREATE DATABASE IF NOT EXISTS libreriaapibbdd;


USE libreriaapibbdd;

SELECT * FROM autor;

SELECT * FROM editorial;


DESCRIBE autor;


DROP TABLE autor, editorial, libro;

/*borrar contenido de una tabla*/
DELETE FROM autor;
DELETE FROM editorial;
