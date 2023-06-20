## Primer Parcial - Proyecto Programacion 2 

# Holding de Empresas
* Desarrolle un sistema para gestionar la información que un holding de empresas desea tener referente
a las empresas que posee, sus vendedores, así como los asesores que trabajan en el holding, teniendo en
cuenta que la información está organizada de la siguiente forma:
* Los vendedores se organizan en una jerarquía de pirámide, es decir, cada vendedor puede captar
otros vendedores para el holding, de manera que un vendedor tendrá a su cargo varios vendedores.
Hay que tener en cuenta que un vendedor sólo podrá trabajar en una empresa y sólo podrá captar
vendedores para la empresa en que trabaja; siendo importante almacenar la fecha de la captación.
Los datos de interés para los vendedores serán el código de vendedor, el nombre y la dirección.
* Las empresas cubrirán diferentes áreas del mercado y una misma área puede ser cubierta por
varias empresas. Es interesante conocer el nombre del área y una descripción de ésta. Las empresas
pueden estar actuando en varios países y en un país pueden estar desarrollando actividades varias
empresas. Sin embargo, cada empresa tendrá su sede en un único país, siendo importante la ciudad
donde se localiza la sede. Por cuestiones fiscales, una empresa puede tener su sede en un país en el
que no esté desarrollando actividad alguna. Los datos de interés para las empresas son el nombre,
la fecha de entrada en el holding, la facturación anual y el número de vendedores que posee.
* Los datos de interés de los países son: el nombre, el PIB, el número de habitantes y la capital.
* Los asesores entran en el holding para dar soporte en cada una de las áreas en las que actúa el
holding. Un asesor puede cubrir varias áreas y un área puede ser cubierta por varios asesores. Un
asesor puede asesorar a varias empresas y una empresa tener varios asesores. Es importante saber
en qué fecha un asesor comienza a trabajar para una empresa en un área determinada. Los datos de
interés de los asesores son el código de asesor, nombre, dirección y la titulación.
* Al sistema podrán acceder tres tipos de usuarios: vendedores (que sólo podrán consultar sus datos),
asesores (que sólo podrán consultar sus datos) y administradores (que administrarán todo).

 **Para ello:**
* Analice los requerimientos anteriores
* Determine los objetos requeridos para implementar ese sistema
* Establezca los atributos que deben tener estos objetos
* Fije los comportamientos que exhibirán estos objetos
* Especifique la forma en que los objetos deben interactuar entre sí para cumplir con los
requerimientos del sistema

El sistema deberá utilizar abstracción, encapsulamiento, herencia, polimorfismo y persistencia (no BD).

La E/S del sistema será exclusivamente por consola (no GUI).

*Se deberán subir a __GitLab__ o __GitHub__ el ejecutable (en formato jar), el código fuente, la documentación
(generada con javadoc) y los diagramas UML de caso-uso, de clases y uno de secuencia (generados con
http://plantuml.com/es o http://www.planttext.com y grabados en formato png)*
