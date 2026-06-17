# GLOVEAF — Sistema de Traducción Asistida de Lengua de Señas 

![logotipo de gloveaf](https://github.com/Selen-Programm/Gloveaf/blob/main/assets/logo-azul.png)

> Ecosistema tecnológico compuesto por un dispositivo vestible instrumentado (guante con sensores) y una aplicación móvil nativa Android para la interpretación y traducción en tiempo real de gestos y lengua de señas a texto y voz.

---

## Descripción del Proyecto

**GLOVEAF** es una solución de ingeniería orientada a mitigar las barreras de comunicación interactiva sufridas por la comunidad sorda e hipoacúsica. El sistema integra el procesamiento de señales de hardware físico con un entorno móvil nativo, permitiendo capturar de manera precisa la cinemática de la mano mediante sensores de flexión y movimiento posicionados en un guante ergonómico, traduciendo estos impulsos de forma fluida a interfaces textuales y de audio sintetizado.

### Pilares Tecnológicos
* **Captura Cinemática:** Hardware instrumentado capaz de vectorizar la posición de los dedos en tiempo real.
* **Interfaz Centrada en el Usuario:** Diseño visual minimalista, accesible y altamente responsivo.
* **Separación de Responsabilidades:** Arquitectura modular orientada al desacoplamiento limpio entre componentes.

---
![logotipo de gloveaf](https://github.com/Selen-Programm/Gloveaf/blob/main/assets/grande-logo-azul.png)

##  Estado Actual del Repositorio (Frontend)

Este repositorio aloja de manera exclusiva el código fuente correspondiente a la **capa de presentación (Frontend)** de la aplicación móvil de GLOVEAF.

### Ficha Técnica del Software
* **Entorno de Desarrollo:** Android Studio
* **Lenguaje / Framework base:** Arquitectura Nativa de Android
* **Espacio de Nombres / Paquete Principal:** `[com.example.gloveaf](https://github.com/Selen-Programm/Gloveaf/tree/main)`

### Componentes Implementados
* **Arquitectura de Vistas:** Módulos jerárquicos de *Activities* y *Fragments* enfocados en la experiencia de usuario.
* **Consola de Configuración:** Interfaz dedicada para el emparejamiento de periféricos de hardware, calibración de transductores y gestión de umbrales de captura.
* **Estructura Reactiva:** Layouts construidos y optimizados para el consumo dinámico de flujos de datos.

*Nota: De forma paralela a este desarrollo técnico, se completó la fase de diseño estético de piezas gráficas corporativas (flyers) para simular la proyección comercial del producto en escenarios competitivos reales.*

[![My Skills](https://skillicons.dev/icons?i=kotlin,figma)](https://skillicons.dev)

---

## Hoja de Ruta Técnica y Próximos Pasos

El proyecto progresa de acuerdo con las fases planificadas de integración de sistemas:

1. **Integración con la Capa de Negocio (Backend):** Conectar las vistas de la aplicación móvil con los motores lógicos y de procesamiento de datos encargados de recibir las tramas de telemetría de los sensores.
2. **Implementación de Modelos Algorítmicos:** Incorporar la lógica y los modelos de traducción encargados de decodificar y asociar los vectores de movimiento del hardware con la sintaxis de la lengua de señas.
3. **Módulo Documental de Optimización:** Centralizar dentro del entorno del proyecto manuales de ingeniería, diagramas de hardware y protocolos de calibración para posibilitar el mantenimiento técnico distribuido y las mejoras por parte de colaboradores.

---

##  Configuración del Entorno de Ingeniería

Para desplegar y analizar el código fuente de la capa de presentación:

1. Clonar el repositorio localmente:
```bash
   git clone [https://github.com/](https://github.com/)Selen-Programm/Gloveaf.git


