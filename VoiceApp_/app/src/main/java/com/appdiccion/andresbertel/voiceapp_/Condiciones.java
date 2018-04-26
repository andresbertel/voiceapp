package com.appdiccion.andresbertel.voiceapp_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Condiciones extends AppCompatActivity {
TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiciones);
        texto= (TextView)findViewById(R.id.terminos);
        texto.setText("VOICEAPP es una plataforma que cuenta con unos términos y condiciones de uso que se describen a  continuación:\n" +
                "TÉRMINOS Y CONDICIONES\n" +
                "\n" +
                "Gracias por utilizar nuestros productos y servicios (“Servicios”). Los Servicios son proporcionados por VOICEAPP ubicado en KM 1 troncal del caribe SINCELEJO-SUCRE.\n" +
                "Estos términos y condiciones crean un contrato entre usted y el NOMBRE EMPRENDIMIETNO,. Mediante la utilización de nuestros Servicios usted está aceptando estas condiciones. Por favor, léalas detenidamente.\n" +
                "\n" +
                "1. USO DE NUESTROS SERVICIOS\n" +
                "La información y recomendaciones incluidas en el portal VOICEAPP tienen la función de alertar y orientar a las usuarias en situación de violencia de genero. La iniciativa VOICEAPP, No asume responsabilidad por la utilización, aplicación y/o procesamiento que los diferentes actores puedan darle a dicha información ni otorgar garantía sobre la exactitud de sus contenidos.\n" +
                "La inscripción del “USUARIO” (entiéndase también, contratista, entidad aliada, personal administrativo y contratista de la entidad aliada, emprendedor, mentor, asesor), en el portal VOICEAPP será considerada como la aceptación de estos Términos y Condiciones. Los datos proporcionados por el emprendedor deben ser correctos, veraces y completos, por lo que el USUARIO asumirá toda responsabilidad sobre la falta de veracidad o exactitud en el suministro de los datos.\n" +
                "El uso de nuestros Servicios no otorga derecho de propiedad intelectual alguno sobre nuestros Servicios o contenido al que acceda. No podrá utilizar el contenido de nuestros Servicios a menos que obtenga el permiso de su propietario o que ello esté permitido por ley. Estas condiciones no le otorgan el derecho de utilizar marca o logotipo alguno utilizado en nuestros Servicios. No elimine, oculte ni modifique ningún aviso legal mostrado en nuestros Servicios o junto a ellos.\n" +
                "Nuestros Servicios muestran cierto contenido que no pertenece a VOICEAPP. Dicho contenido es responsabilidad exclusiva de la entidad que lo pone a disposición. Podremos revisar contenido para determinar si es ilegal o si infringe nuestras políticas, y podremos eliminar o rechazar la visualización de contenido que razonablemente consideremos que infringe nuestras políticas o la ley. Sin embargo, esto no significa que revisemos contenido, por lo que no debe suponer que lo haremos.\n" +
                "\n" +"2. INFORMACIÓN\n" +
                "Los datos de los usuarios, que se registren al portal VOICEAPP  serán tratados cumpliendo con las políticas de seguridad de la iniciativa en cuando a la integridad de la información.\n" +
                "VOICEAPP puede utilizar información de los CLIENTES-USUARIOS  para contactarlos en caso de requerirse. Así mismo, tal información podrá compartirla con las instituciones aliadas, entes públicos, privados y demás actores del ecosistema de emprendimiento TIC con los cuales la Iniciativa tiene relación directa, como parte del proceso de acompañamiento, visibilidad, difusión, mentorías, asesorías y las otras herramientas que implemente la Iniciativa Apps.co para el cumplimiento de su misión.\n" +
                "\n" +"3. CONVENIOS Y ALIANZAS\n" +
                "La cancelación de cualquier suscripción a un producto que no es de la iniciativa Apps.co, está sujeta a los términos entre el usuario y el tercero que proporciona el producto. La cancelación de la suscripción del usuario no modificará su obligación de pagar todos los cargos realizados o cualquier importe que se adeude de otro modo.\n" +
                "La iniciativa Apps.co realizará la activación de los cursos y/o servicios de formación en desarrollo de aplicaciones web y móviles de acuerdo a los tiempos estimados para tal fin y teniendo en cuenta el tipo y nivel de cada curso.\n" +
                "\n" +"4. SOPORTE Y SERVICIO AL CLIENTE\n" +
                "Soporte al Cliente se refiere a todo soporte o asesoría prestada al usuario en virtud de su participación en la iniciativa a través del portal: VOICEAPP. La iniciativa VOICEAPP garantiza que el soporte a los usuarios, se prestará con diligencia y aptitudes profesionales, teniendo presente que el tiempo de respuesta de cada petición o solicitud puede tomar hasta 15 días hábiles contados a partir del día siguiente de la petición o solicitud, dependiendo de la severidad del caso según lo que indica la ley Estatutaria de Derecho de Petición 1755 de 2015.\n" +
                "\n" +"5. USO DE LA PLATAFORMA\n" +
                "La iniciativa VOICEAPP se reserva el derecho a realizar la eliminación y activación de Usuarios para el uso de la plataforma a aquellos que demuestren comportamientos nocivos para la comunidad de emprendimiento y/o utilicen lenguajes de carácter ofensivo en los foros y servicios de comunicaciones que provee la plataforma.\n" +
                "El Usuario podrá utilizar la plataforma de la iniciativa VOICEAPP solo de acuerdo con los términos de este acuerdo. No podrá utilizar técnicas de ingeniería inversa, descompilar, desensamblar ni eludir las limitaciones técnicas del producto, salvo que la legislación aplicable lo permita a pesar de esta limitación. No podrá desactivar, alterar ni, de otro modo, intentar burlar cualquier mecanismo de seguridad con la intención de conocer, alterar y/o atentar contra la seguridad de la información y de los servicios que provee la iniciativa VOICEAPP.\n" +
                "Tampoco podrá subir, enviar o de otra manera transmitir algún contenido que pueda resultar contrario a la ley, moral, orden público, contenido dañino, abusivo, despectivo, difamatorio, vulgar, obsceno, invasivo o privado, racista, xenófobos, o cualquier comportamiento objetable.\n" +
                "No podrá subir, enviar o transmitir algún contenido sin acreditar legalmente los derechos necesarios para hacerlo (por ejemplo la información al interior de una empresa, propietaria y confidencial aprendida o divulgada como parte de relaciones del empleo o según los términos de acuerdos de un contrato). Así mismo, está prohibido enviar, subir o transmitir algún contenido comercial o publicitario.\n" +
                "No podrá subir, enviar o transmitir algún material que contenga virus o algún código computacional como archivos o programas diseñados para interrumpir, destruir o limitar la funcionalidad de algún software, hardware o equipo de telecomunicaciones.\n" +
                "\n" + "6. RESPONSABILIDAD DE LAS CUENTAS\n" +
                "Los usuarios también serán responsables de mantener la confidencialidad de cualquier credencial de autenticación no pública relacionada con el uso que hace de los Servicios.\n" +
                "\n" + "7. POLÍTICA DE PRIVACIDAD\n" +
                "De conformidad a lo dispuesto en el artículo 15 y 20 de la Constitución Política de Colombia y la Ley Estatutaria 1581 de 2012 se señala lo siguiente:\n" +
                "VOICEAPP, pone en conocimiento de todos quienes acceden a la iniciativa VOICEAPP, la siguiente Política de Privacidad, a fin de resguardar la seguridad, confidencialidad y privacidad del usuario y/o visitante de este sitio.\n" +
                "Estas políticas tienen por finalidad asegurar la correcta utilización de la información recopilada a través de las visitas de este sitio y de sus contenidos.\n" +
                "7.1. SOBRE LOS DATOS RECOPILADOS.\n" +
                "VOICEAPP recopila datos de los suscriptores, usuarios y/o visitantes que hagan uso de este portal, a través de dos mecanismos:\n" +
                "Mecanismos Automáticos: Son aquellos procesos informáticos realizados para generar registros de las actividades de los visitantes de sitios Web y cuyo objeto es establecer patrones de actividad, navegación y audiencia, que no implican la identificación personal de aquellos suscriptores, usuarios y/o visitantes que accedan a VOICEAPP\n" +
                "VOICEAPP se reserva el derecho de usar dicha información general, a fin de establecer criterios que mejoren los contenidos de este sistema, en todo caso siempre disociados de la persona que dejó los datos en su navegación.\n" +
                "Mecanismos Manuales: Son requerimientos formales y expresos de información a los suscriptores, usuarios y/o visitantes del portal que implican la recolección de datos personales tales como nombre, apellidos, domicilio, correo electrónico, ocupación, etc.\n" +
                "VOICEAPP recopila estos datos con el objeto de mejorar la calidad de información del portal y dar una mejor atención ante consultas de la ciudadanía.\n" +
                "7.2. SOBRE EL TRATAMIENTO DE LOS DATOS.\n" +
                "Respecto de la entrega de información recopilada por medio de los mecanismos automáticos antes señalados y, que no contengan identificación personal de los suscriptores, usuarios y/o visitantes de la página, ésta podrá ser utilizada para informar a entidades públicas, gubernamentales o a terceros sobre patrones de actividad, navegación, audiencia y caracterización general de este sistema.\n" +
                "Respecto de los datos personales de los usuarios, recolectados a través de mecanismos manuales u otros medios, éstos serán tratados con el objeto de mejorar la calidad de información de la iniciativa y dar una mejor atención ante consultas de la ciudadanía.\n" +
                "Al aceptar las presentes Políticas de Privacidad, usted autoriza a VOICEAPP para:\n" +
                "Comunicar a otros organismos del Estado u otros terceros aliados sus datos personales con el objeto de mejorar la calidad de información de la iniciativa y dar una mejor atención ante consultas de la ciudadanía.\n" +
                "Comunicar a terceros información estadística elaborada a partir de los datos personales de sus usuarios, cuando de dichos datos no sea posible identificar individualmente a los titulares (datos disociados), de conformidad a la Ley.\n" +
                "Aparte de las autorizaciones establecidas anteriormente, VOICEAPP mantendrá la debida confidencialidad de los datos personales y no los trasmitirá a terceros, salvo cuando se deban entregar en razón de un mandato legal o una orden emanada de la autoridad Judicial que así lo requiera.\n" +
                "7.3. DERECHOS DEL TITULAR DE LOS DATOS.\n" +
                "En todo momento, el usuario podrá ejercer los derechos otorgados por la Ley Estatutaria 1581 de 2012.\n" +

                "\n" +"8. RESPONSABILIDAD\n" +
                "Excepto por lo que se establece expresamente en estas condiciones o en condiciones adicionales, ni VOICEAPP ni sus proveedores o distribuidores realizan promesa alguna específica sobre los servicios. por ejemplo, no asumimos ningún compromiso respecto al contenido de los servicios, la función específica de los servicios, o su confiabilidad, disponibilidad o capacidad para satisfacer sus necesidades, por lo cual proporcionamos los servicios “tal como están”.\n" +
                "\n" + "9. AUTORIZACIÓN\n" +
                "Los usuarios de la iniciativa declaran conocer y aceptar la circunstancia relativa a que este servicio público podrá, en cualquier momento, modificar todo o parte de las presentes condiciones de uso, conforme con la legislación vigente o políticas del Gobierno Nacional.\n");
    }
}
