package CuatroDestinos.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MenuApp extends AppCompatActivity {

    ArrayList<Personaje> personajes = new ArrayList<>();
    ArrayList<Situacion> situaciones = new ArrayList<>();
    private Timer timer;

    private void inicializarDatos() {

        situaciones.addAll(Arrays.asList(
                new Situacion("Llegais a un nuevo planeta desconocido pero no hay suministros para todos"),
                new Situacion("Estais en una isla desierta después de que se estrelle vuestro avión"),
                new Situacion("Ha habido un apocalipsis en la tierra y os vais en una nave espacial"),
                new Situacion("El barco en el que ibais se hunde, teneis que salir huyendo en una balsa"),
                new Situacion("Estais en medio de una ciudad futurista colapsando y podeis usar un coche para huir"),
                new Situacion("Os encontrais en un complejo y os asalta un grupo armado, debereis salir de allí"),
                new Situacion("Se organiza una fuga en la prisión y los presos de las celdas cercanas hablais para saber como escapar"),
                new Situacion("Despertais en un batisterio romano subterraneo y debeis escapar de alli")
        ));
    }

    // Coger personaje y situación
    private static Personaje seleccionarPersonajeAleatorio(ArrayList<Personaje> personajes) {
        Random rand = new Random();
        int indiceAleatorio = rand.nextInt(personajes.size());
        return personajes.get(indiceAleatorio);
    }

    private static Situacion seleccionarSituacion(ArrayList<Situacion> situaciones) {
        Random rand = new Random();
        int indiceAleatorio = rand.nextInt(situaciones.size());
        return situaciones.get(indiceAleatorio);
    }

    // Botón ayuda
    public void mostrarAyuda() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert, null);

        TextView messageTextView = view.findViewById(R.id.dialogMessage);
        Button closeButton = view.findViewById(R.id.dialogButton);

        messageTextView.setText("En este juego, se os presentará una situación específica. Vuestro objetivo es discutir y razonar entre vosotros, " +
                "considerando las características de vuestros personajes. " +
                "El juego consiste en encontrar la mejor manera de resolver la situación, ya que solo podrán sobrevivir 4 personajes. ¡Elegid sabiamente," +
                " pues los demás serán abandonados!" + "\n" + "\n" + "Modo de juego: un jugador establece la situación con el botón Situación, y todos los jugadores usaran el botón de Personaje " +
                "para que se les muestre el personajes a interpretar" + "\n" + "\n" + "Eventos: cada cierto tiempo apareceran eventos que afecten a la partida. Tened cuidado e intentar resolverlos" +
                " juntos. En caso de que coincidan dos eventos solo se tendra en cuenta el primero que aparezca, a menos que uno este marcado como importante o decisivo, entonces " +
                "si un segundo aparece se cogera y debereis lidiar con ambos");

        final AlertDialog alertDialog = builder.setView(view).create();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    // Personajes y creación
    private static Personaje crearPersonajeAleatorio() {
        Random rand = new Random();
        String[] nombres = {"Emma", "Javier", "Aisha", "Liam", "Isabella","Alejandro", "Javier", "Carlos", "Miguel", "Luis", "David", "Sergio", "José", "Daniel",
                "Juan", "Antonio", "Pedro", "Manuel", "Fernando", "Pablo", "Jorge", "Raúl", "Alberto", "Ricardo",
                "Ángel", "Diego", "Iván", "Roberto", "Adrián", "Rafael", "Alejandro", "Andrés", "Héctor", "Emilio",
                "Ana", "María", "Laura", "Sara", "Elena", "Lucía", "Carmen", "Isabel", "Marta", "Natalia",
                "Patricia", "Eva", "Lourdes", "Rosa", "Beatriz", "Clara", "Alba", "Susana", "Lorena", "Cristina",
                "Mónica", "Vanessa", "Silvia", "Mireia", "Angy", "Alicia", "Pilar", "Nuria", "Jessy", "Eufrasio", "Amenio", "Oriol", "Rubiales", "Pepe",
                "Juanjo", "Cocoliso", "Chimuelo", "Moncho", "Safria", "Zaripastra", "Birgolini", "Amerida", "Bienvenida", "Jenny", "Melinda", "Esperanza", "Josefina",
                "Benito", "Paco", "Julian", "Margarita", "Froilan"};
        String[] problemas = {"Veganismo estricto", "Claustrofobia", "Introvertido", "ADHD","Depresión", "Ansiedad", "Trastorno obsesivo-compulsivo",
                "Trastorno de estrés postraumático", "Fobia social", "Trastorno de pánico", "bipolar", "Esquizofrenia", "TDAH", "autismo", "alcoholemia", "drogadiccion",
                "Juego patológico", "insomnio", "narcisista", "Trastorno de la conducta alimentaria nocturna", "Trastorno de la compra compulsiva", "antisocial",
                "negativismo", "sadismo", "masoquista", "mandamas", "ego desmedido", "compulsividad", "desconfiado", "autocomplaciente", "avaricia", "perezoso", "agresividad", "sumision",
                "supersticioso", "megalomania", "trastorno de la identidad sexual", "paranoico", "se cree perro", "se cree caballo", "Manipulación emocional", "Egoísmo extremo",
                "Adicción a las redes sociales", "Comportamiento pasivo-agresivo", "Perfeccionismo obsesivo", "Competitividad desmedida", "Intolerancia", "Adicción al juego",
                "Conducta adictiva a la comida", "Dependencia emocional", "Actitud pesimista crónica", "Exhibicionismo", "Comportamiento egocéntrico", "Desorganización crónica",
                "Hostilidad constante", "Conducta crítico-destructiva", "Obsesión por el control", "Desconfianza constante", "Actitud derrotista", "Rigidez en la toma de decisiones",
                "Obsesión por el orden", "analfabetismo", "asperger", "Personalidad multiple(niño,anciano)", "Personalidad multiple(anciana,soldado de vietnam)",
                "Personalidad multiple(bebe,asesino en serie)", "vago", "torpe", "tartamudo", "mudo", "sordo", "ciego", "sida", "diarrea explosiva", "tuerta"};

        String nombreAleatorio = nombres[rand.nextInt(nombres.length)];
        String generoAleatorio = obtenerGenero(nombreAleatorio);
        int edadAleatoria = rand.nextInt(91) + 10;
        String profesionAleatoria = obtenerProfesion(generoAleatorio, rand);
        String problemaAleatorio = problemas[rand.nextInt(problemas.length)];

        return new Personaje(nombreAleatorio, edadAleatoria,  profesionAleatoria, problemaAleatorio, generoAleatorio);
    }

    private static String obtenerGenero(String nombre) {
        if (nombre.endsWith("a") || nombre.endsWith("i") || nombre.endsWith("y") || nombre.endsWith("u") || nombre.endsWith("z")) {
            return "Mujer";
        } else {
            return "Hombre";
        }
    }

    private static String obtenerProfesion(String genero, Random rand) {
        String[] profesionesMasculinas = {"Ingeniero", "Psicólogo", "Médico", "Estudiante", "Arquitecto", "Programador", "Piloto", "Científico",
                "Chef", "Empresario", "Mecánico", "Diseñador","Abogado", "Policía", "Carpintero", "Constructor", "Electricista", "Astronauta", "Atleta", "Escritor",
                "Artista", "Proxeneta", "Camello", "Coctelero", "Dj", "Gerente de un club de alterne", "Profesor", "Periodista de prensa rosa",
                "Jefe de secta religiosa", "Periodista", "Coordinador de eventos", "Investigador", "Cómico", "Actor porno", "Policia", "Limpiador", "Barrendero", "nini",
                "ex-militar", "Historiador", "Peluquero", "Influencer", "Cantante"};
        String[] profesionesFemeninas = {"Ingeniera", "Psicóloga", "Médica", "Estudiante", "Arquitecta", "Programadora", "Piloto", "Científica", "Chef", "Empresaria",
                "Mecánica", "Diseñadora","Abogada", "Policía", "Carpintera", "Constructora", "Electricista", "Astronauta", "Atleta", "Escritora", "Artista", "Contable",
                "Prostituta", "Camello", "Futbolista", "DJ", "Secretaria", "Gerente de un club de alterne", "Profesora", "Periodista", "Periodista de prensa rosa",
                "Jefa de secta religiosa", "Coordinadora de eventos", "Investigadora", "Cómica", "Actriz porno", "Policia", "Limpiadora", "Barrendera", "nini", "ex-militar", "Historiadora",
                "Peluquera", "Influencer", "Cantante"};

        if (genero.equals("Mujer")) {
            return profesionesFemeninas[rand.nextInt(profesionesFemeninas.length)];
        } else {
            return profesionesMasculinas[rand.nextInt(profesionesMasculinas.length)];
        }
    }

    // Eventos
    public void mostrarEventoAleatorio() {
        Random random = new Random();
        int dadoEvento = random.nextInt(6) + 1;

        String[] eventosAleatorios;
        if (dadoEvento <= 3) {
            eventosAleatorios = obtenerEventosNormales();
        } else if (dadoEvento <= 5) {
            eventosAleatorios = obtenerEventosImportantes();
        } else {
            eventosAleatorios = obtenerEventosDecisivos();
        }
        int numAleatorio = random.nextInt(eventosAleatorios.length);

        SpannableString spannableString = new SpannableString(eventosAleatorios[numAleatorio]);
        int index = eventosAleatorios[numAleatorio].indexOf("Evento");

        if (index != -1) {
            if (dadoEvento <= 3) {
                int color = obtenerColorSegunTipoEvento(dadoEvento);
                spannableString.setSpan(new ForegroundColorSpan(color), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (dadoEvento <= 5) {
                int color = obtenerColorSegunTipoEvento(dadoEvento);
                spannableString.setSpan(new ForegroundColorSpan(color), 0, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                int color = obtenerColorSegunTipoEvento(dadoEvento);
                spannableString.setSpan(new ForegroundColorSpan(color), 0, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alertevent, null);

        TextView messageTextView = view.findViewById(R.id.dialogMessage);
        Button closeButton = view.findViewById(R.id.dialogButton);

        messageTextView.setText(spannableString);

        final AlertDialog alertDialog2 = builder.setView(view).create();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog2.dismiss();
            }
        });
        alertDialog2.show();
    }

    // Color eventos
    private int obtenerColorSegunTipoEvento(int dadoEvento) {
        switch (dadoEvento) {
            case 1:
            case 2:
            case 3:
                return Color.GREEN; // Evento normal
            case 4:
            case 5:
                return Color.parseColor("#6738FE"); // Evento importante
            case 6:
                return Color.RED; // Evento decisivo
            default:
                return Color.BLACK; // Color base
        }
    }

    private String[] obtenerEventosNormales() {
        return new String[]{"Evento: hace un dia tranquilo, parece que no sucede nada fuera de lo común",
                "Evento: encuentras un objeto, parece un paraguas muy pesado",
                "Evento: te caes en un agujero y te tuerces el tobillo, la proxima mira mejor",
                "Evento: encuentras un mapa y lo guardas porque sera util",
                "Evento: te pica un insecto y te enfermas",
                "Evento: Ha ocurrido un fallo en las comunicaciones, a partir de ahora omitireis la última sílaba al hablar",
                "Evento: hace un dia tranquilo, parece que no sucede nada fuera de lo común",
        };
    }

    private String[] obtenerEventosImportantes() {
        return new String[]{"Evento importante: encontrais a una persona herida que os da un objeto, resulta ser un frasco extraño",
                "Evento importante: encontrais un botiquin tirado en el suelo",
                "Evento importante: el jugador de tu izquierda se corta en la mano y le queda inutilizada",
                "Evento importante: empieza a caer lluvia acida, tu y los jugadores que estan a ambos lados de ti quedais heridos por ello (se puede evitar con el objeto paraguas pesado)",
                "Evento importante: aparece un animal, sin pensarlo le pegas con lo primero que encuentras y resulta ser tu compañero. El jugador que tienes delante no podra usar su brazo derecho",
                "Evento importante: desafio de habilidad, deberas cruzar un acantilado con tu compañero de la derecha, anda con el encima durande 5 metros o ambos caereis por el acantilado",
                "Evento importante: tu y el jugador de tu izquierda os enamorais, pero a causa de tu problema os separais, dejando mucho odio entre vosotros. debereis opinar lo contrario todo el rato",
        };
    }

    private String[] obtenerEventosDecisivos() {
        return new String[]{"Evento decisivo: Alguien ha malgastado las raciones que habia,en vez de 4 personas al final solo pueden haber 3",
                "Evento decisivo: el jugador de tu derecha cae enfermo (se puede curar con el objeto frasco extraño)",
                "Evento decisivo: al jugador de tu izquierda le atraviesa el costado una barra de hierro que había suelta (se puede sanar con el objeto botiquín)",
                "Evento decisivo: aparece una persona extraña y secuestra al jugador más joven, ya no se supo nada mas de él",
                "Evento decisivo: te alias con el jugador que tienes delante ya que descubris que os llevais bastante bien, pero si ese jugador es descartado tu tambien lo seras",
                "Evento decisivo: veis un extraño dispositivo, al tocarlo emite una luz y tras un fogonazo de luz el entorno a cambiado. cambiar la situacion con el boton de situacion",
        };
    }

    // Temporizador eventos
    private void iniciarTemporizador() {
        Random random = new Random();
        int delayInt =  random.nextInt((30 * 60 * 1000 - 15 * 60 * 1000) + 1) + 15 * 60 * 1000;
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mostrarEventoAleatorio();
                        }
                    });
                }
            }, delayInt, obtenerIntervaloAleatorio());
        }

    }

    private int obtenerIntervaloAleatorio() {
        Random random = new Random();
        return random.nextInt((30 * 60 * 1000 - 15 * 60 * 1000) + 1) + 15 * 60 * 1000;
    }

    private void detenerTemporizador() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnPJ,btnSitu,btnInfo;
        TextView textSitu,textPJ;
        ImageView imagen;

        btnPJ = findViewById(R.id.btnPJ);
        btnSitu = findViewById(R.id.btnSitu);
        btnInfo = findViewById(R.id.btnInfo);
        textPJ = findViewById(R.id.textPJ);
        textSitu = findViewById(R.id.textSitu);

        inicializarDatos();

        for (int i = 0; i < 300; i++) {
            personajes.add(crearPersonajeAleatorio());
        }

        btnPJ.setOnClickListener(view -> {
            Personaje personajeSeleccionado = seleccionarPersonajeAleatorio(personajes);
            String detallesPersonaje = "Nombre: " + personajeSeleccionado.nombre + "\n" +
                    "Edad: " + personajeSeleccionado.edad + "\n" +
                    "Profesión: " + personajeSeleccionado.profesion + "\n" +
                    "Problema: " + personajeSeleccionado.problema + "\n" +
                    "Sexo: " + personajeSeleccionado.sexo;

            textPJ.setText(detallesPersonaje);
        });

        btnSitu.setOnClickListener(view -> {
            Situacion SituacionSeleccionada = seleccionarSituacion(situaciones);
                textSitu.setText(SituacionSeleccionada.situacion);
        });

        btnInfo.setOnClickListener(view -> {
            mostrarAyuda();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        iniciarTemporizador();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detenerTemporizador();
    }
}