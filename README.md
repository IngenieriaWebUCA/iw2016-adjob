# AdJob - Ingeniería Web 2016 - Universidad de Cádiz

### Versión
1.0

### Versión de las herramientas
- sts-3.7.3.RELEASE
- gvNIX-1.4.1.RELEASE
- apache-tomcat-8.0.32

### Sincronizar el repositorio

- La primera vez, ejecutar los siguientes comandos en el directorio donde vaya a almacenar los archivos de la aplicación ("Workspace"):

```sh
$ git init
$ git remote add origin git@github.com:IngenieriaWebUCA/iw2016-adjob.git
$ git pull
```

- El resto de las veces:
Para descargar archivos:
```sh
$ git pull origin master
```

Para subir nuevos archivos:
```sh
$ git add --all
$ git commit -m "Descripción de los cambios efectuados en el proyecto"
$ git push origin master
```
