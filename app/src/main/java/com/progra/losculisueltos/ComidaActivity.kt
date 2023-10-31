package com.progra.losculisueltos

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.progra.losculisueltos.databinding.ActivityComidaBinding
import com.progra.losculisueltos.dataclases.Comidas
import com.progra.losculisueltos.recyclerviews.MenuComidasActivity

class ComidaActivity : AppCompatActivity() {
    lateinit var binding: ActivityComidaBinding
    val context: Context = this
    val desayunos = inicializarDesayuno()
    val almuerzos = inicializarAlmuerzo()
    val cenas = inicializarCena()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComidaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonMenu.setOnClickListener {
            finish()
        }

        binding.cardViewCalculadoraCalorica.setOnClickListener {
            val intent: Intent = Intent(context, CalculadoraActivity::class.java)
            startActivity(intent)
        }
        binding.buttonUser.setOnClickListener {
            val intent: Intent = Intent(context, PerfilActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewDesayuno.setOnClickListener{
            val intent: Intent = Intent(context, MenuComidasActivity::class.java)
            val arrayList = ArrayList(desayunos)
            intent.putExtra(CLAVE_LISTA, arrayList)
            startActivity(intent)
        }
        binding.cardViewAlmuerzo.setOnClickListener{
            val intent: Intent = Intent(context, MenuComidasActivity::class.java)
            val arrayList = ArrayList(almuerzos)
            intent.putExtra(CLAVE_LISTA, arrayList)
            startActivity(intent)
        }
        binding.cardViewCena.setOnClickListener{
            val intent: Intent = Intent(context, MenuComidasActivity::class.java)
            val arrayList = ArrayList(cenas)
            intent.putExtra(CLAVE_LISTA, arrayList)
            startActivity(intent)
        }

    }

    fun inicializarDesayuno(): List<Comidas>{
        val desayuno = mutableListOf<Comidas>(
             Comidas(
                nombre = "Batido de Espinacas y Platano",
                calorias = 175,
                ingredientes = "-1 taza de espinacas frescas\n-1 plátano maduro\n-1/2 taza de leche desnatada (u otra alternativa baja en calorías, como leche de almendra sin azúcar)\n-1 cucharadita de miel (opcional)\n-Hielo al gusto",
                preparacion = "Coloca las espinacas, el plátano, la leche (o alternativa de leche baja en calorías), la miel (si lo deseas) y el hielo en una licuadora. Mezcla hasta obtener una consistencia suave y homogénea.",
                imagenC = R.drawable.batido_espinacas_platano
            ),
            Comidas(
                nombre = "Yogurt con Frutas y Granola",
                calorias = 225,
                ingredientes = "-1 taza de yogur natural bajo en grasa\n-1/2 taza de frutas frescas (como fresas, arándanos o frambuesas)\n-2 cucharadas de granola baja en calorías",
                preparacion = "En un tazón, coloca el yogur bajo en grasa. Agrega las frutas frescas y la granola. Mezcla todo suavemente.",
                imagenC = R.drawable.yogurt_frutas_granola
            ),
            Comidas(
                nombre = "Tostadas con Palta",
                calorias = 275,
                ingredientes = "-2 rebanadas de pan integral tostado\n-1/2 aguacate maduro\n-Tomate en rodajas finas\n-Pimiento rojo en rodajas finas\n-Pizca de sal y pimienta\n-Opcional: unas hojas de espinacas baby",
                preparacion = "Tuesta las rebanadas de pan integral. Mientras se tuestan, corta el aguacate por la mitad y quita el hueso. Luego, corta el aguacate en rebanadas finas. Una vez que las tostadas estén listas, unta las rebanadas de aguacate sobre el pan. Agrega las rodajas de tomate y pimiento rojo, y sazona con sal y pimienta. Si lo deseas, puedes añadir espinacas baby.",
                imagenC = R.drawable.tostadas_de_aguacate
            ),
            Comidas(
                nombre = "Omelette de Espinacas y Champiñones",
                calorias = 325,
                ingredientes = "-2 huevos\n-1 taza de espinacas frescas\n-1/2 taza de champiñones en rodajas\n-1/4 de taza de queso rallado bajo en grasa\n-Sal y pimienta al gusto",
                preparacion = "En un tazón, bate los huevos y añade una pizca de sal y pimienta. Calienta una sartén antiadherente y rocía con aceite en aerosol. Saltea las espinacas y los champiñones hasta que estén tiernos. Vierte los huevos batidos sobre las verduras y cocina hasta que estén cuajados. Espolvorea el queso rallado sobre el omelette y espera a que se derrita.",
                imagenC = R.drawable.omelette_champinhones_espinacas
            ),
            Comidas(
                nombre = "Avena con Frutas y Nueces",
                calorias = 375,
                ingredientes = "-1/2 taza de avena\n-1 taza de leche (puedes usar leche desnatada o alternativas de leche baja en calorías)\n-1/2 taza de frutas frescas (como plátano, fresas o manzana)\n-1 cucharada de nueces picadas\n-1 cucharadita de miel (opcional)",
                preparacion = "Cocina la avena siguiendo las instrucciones del paquete, utilizando leche. Sirve la avena en un tazón y agrega las frutas frescas, las nueces picadas y, si lo deseas, un toque de miel.",
                imagenC = R.drawable.avena_frutas_nueces
            ),
            Comidas(
                nombre = "Tostadas de Salmón y Aguacate",
                calorias = 375,
                ingredientes = "-2 rebanadas de pan integral tostado\n-2 oz (aproximadamente 56 g) de salmón ahumado\n-1/2 aguacate maduro\n-Rodajas de pepino y cebolla roja al gusto\n-Zumo de limón\n-Pimienta negra",
                preparacion = "Tuesta las rebanadas de pan integral. Mientras se tuestan, corta el aguacate por la mitad y quita el hueso. Luego, corta el aguacate en rebanadas finas y rocía con zumo de limón. Una vez que las tostadas estén listas, coloca las rebanadas de aguacate sobre el pan. Agrega las lonchas de salmón ahumado, las rodajas de pepino y cebolla roja. Sazona con pimienta negra al gusto.",
                imagenC = R.drawable.tostadas_salmon_aguacate
            ),
            Comidas(
                nombre = "Tostadas Francesas",
                calorias = 450,
                ingredientes = "-2 rebanadas de pan brioche\n-2 huevos\n-1/4 de taza de leche entera\n-1 cucharadita de azúcar\n-1/2 cucharadita de canela\n-Mantequilla\n-Jarabe de arce",
                preparacion = "En un tazón, bate los huevos y mezcla con la leche, azúcar y canela. Calienta una sartén antiadherente y derrite una pequeña cantidad de mantequilla a fuego medio. Sumerge las rebanadas de pan brioche en la mezcla de huevo y leche, luego colócalas en la sartén caliente. Cocina las tostadas por ambos lados hasta que estén doradas. Sirve las tostadas francesas con mantequilla y jarabe de arce.",
                imagenC = R.drawable.tostadas_francesas
            ),
            Comidas(
                nombre = "Burrito",
                calorias = 550,
                ingredientes = "-2 tortillas de harina grandes\n-4 huevos revueltos\n-1/2 taza de queso cheddar rallado\n-1/4 de taza de tocino cocido y desmenuzado\n-Salsa picante al gusto\n-Aguacate en rodajas (opcional)",
                preparacion = "Prepara los huevos revueltos en una sartén hasta que estén casi cocidos. Calienta las tortillas de harina en una sartén o en el microondas para ablandarlas. Rellena cada tortilla con los huevos revueltos, queso cheddar, tocino y salsa picante al gusto. Si lo deseas, añade rodajas de aguacate. Dóblalas en forma de burrito y sirve.",
                imagenC = R.drawable.burrito
            ),
            Comidas(
                nombre = "Batido de Proteínas",
                calorias = 550,
                ingredientes = "-1 taza de leche de almendra (o leche entera)\n-1 plátano maduro\n-2 cucharadas de mantequilla de cacahuate\n-1 cucharada de miel\n-1 cucharada de proteína en polvo (sabor a elección)\n-Un puñado de nueces o almendras",
                preparacion = "En una licuadora, coloca la leche de almendra, el plátano, la mantequilla de cacahuate, la miel, la proteína en polvo y las nueces. Licúa hasta que la mezcla esté suave y homogénea. Sirve el batido en un vaso.",
                imagenC = R.drawable.batido_proteinas
            )
        )
        return desayuno
    }

    fun inicializarAlmuerzo(): List<Comidas>{
        val almuerzo = mutableListOf<Comidas>(
            Comidas(
                nombre = "Ensalada de Pollo a la Parrilla",
                calorias = 325,
                ingredientes = "-Pechugas de pollo (1 porción)\n-Aceite de oliva (para aderezar el pollo)\n-Ajo picado\n-Sal y pimienta al gusto\n-Verduras de hojas verdes (lechuga, espinacas, rúcula)\n-Tomate\n-Pepino\n-Pimiento\n-Vinagreta baja en calorías o jugo de limón (opcional)",
                preparacion = "Adereza las pechugas de pollo con un poco de aceite de oliva, ajo picado, sal y pimienta. Calienta una parrilla o sartén a fuego medio-alto y cocina el pollo hasta que esté dorado y cocido por completo. Deja que el pollo repose unos minutos antes de cortarlo en tiras. Prepara una ensalada con una mezcla de verduras de hojas verdes (lechuga, espinacas, rúcula), tomate, pepino y pimiento. Agrega las tiras de pollo a la ensalada. Puedes aderezar la ensalada con una vinagreta baja en calorías o con jugo de limón.",
                imagenC = R.drawable.ensalada_pollo_parrilla
            ),
            Comidas(
                nombre = "Sopa de Lentejas",
                calorias = 275,
                ingredientes = "-Lentejas secas\n-Aceite de oliva\n-Cebolla\n-Zanahoria\n-Apio\n-Caldo de verduras bajo en sodio\n-Hierbas y especias al gusto (tomillo, laurel, pimienta negra, etc.)\n-Sal (si es necesario)\n-Pan integral bajo en calorías (opcional)",
                preparacion = "En una olla grande, calienta un poco de aceite de oliva y saltea cebolla, zanahoria y apio picados hasta que estén tiernos. Agrega lentejas secas (previamente enjuagadas) y caldo de verduras bajo en sodio. Lleva la mezcla a ebullición, reduce el fuego y cocina a fuego lento durante aproximadamente 30-40 minutos o hasta que las lentejas estén tiernas. Añade hierbas y especias al gusto, como tomillo, laurel, pimienta negra, y sal si es necesario. Sirve caliente y, si lo deseas, puedes acompañar con una rebanada de pan integral bajo en calorías.",
                imagenC = R.drawable.sopa_lentejas
            ),
            Comidas(
                nombre = "Pechuga de Pavo al Horno con Brócoli y Patatas Asadas",
                calorias = 375,
                ingredientes = "-Pechugas de pavo\n-Brócoli\n-Patatas\n-Aceite de oliva\n-Sal y pimienta\n-Hierbas al gusto (romero, tomillo, etc.)\n-Ajo picado (opcional)",
                preparacion = "Precalienta el horno a 200°C. Corta las patatas en trozos pequeños y colócalas en una bandeja para horno. Rocía con un poco de aceite de oliva y sazona con sal, pimienta y tus hierbas favoritas, como romero o tomillo. Asa las patatas en el horno durante unos 20-25 minutos o hasta que estén doradas y tiernas. Mientras tanto, sazona las pechugas de pavo con hierbas, ajo picado, sal y pimienta. En otra bandeja para horno, coloca las pechugas de pavo y el brócoli en trozos. Asa las pechugas de pavo y el brócoli en el horno durante aproximadamente 20-25 minutos o hasta que el pavo esté cocido y el brócoli esté tierno. Sirve las pechugas de pavo con el brócoli y las patatas asadas.",
                imagenC = R.drawable.pechuga_pavo_brocoli_patatas_asadas
            ),
            Comidas(
                nombre = "Pollo con Verduras Asadas",
                calorias = 425,
                ingredientes = "-Pechuga de pollo\n-Verduras asadas (pimientos, calabacín, berenjenas)\n-Aceite de oliva\n-Sal y pimienta\n-Hierbas secas (tomillo, romero)\n-Ajo picado (opcional)",
                preparacion = "Precalienta el horno a 200°C. Coloca las pechugas de pollo en una bandeja para horno y adereza con aceite de oliva, sal, pimienta, hierbas secas y ajo picado (si lo deseas). En otra bandeja para horno, coloca las verduras asadas y adereza con aceite de oliva, sal y pimienta. Asa tanto el pollo como las verduras en el horno durante unos 25-30 minutos o hasta que el pollo esté cocido y las verduras estén tiernas. Sirve el pollo con las verduras asadas.",
                imagenC = R.drawable.pollo_verduras_asadas
            ),
            Comidas(
                nombre = "Espaguetis de Calabacín con Salsa de Tomate y Albóndigas de Pavo",
                calorias = 375,
                ingredientes = "-Calabacines (para hacer espaguetis de calabacín)\n-Albóndigas de pavo (bajas en grasa)\n-Salsa de tomate (sin azúcar añadido)\n-Sal y pimienta\n-Queso parmesano rallado (opcional)",
                preparacion = "Usa un rallador de verduras para hacer espaguetis de calabacín con los calabacines. Cocina las albóndigas de pavo según las instrucciones del paquete. Calienta la salsa de tomate en una cacerola y sazona con sal y pimienta. Sirve los espaguetis de calabacín con las albóndigas de pavo y la salsa de tomate. Si lo deseas, espolvorea queso parmesano rallado por encima.",
                imagenC = R.drawable.espaguetis_calabacin_tomate_pavo
            ),
            Comidas(
                nombre = "Ensalada de Atún y Garbanzos",
                calorias = 325,
                ingredientes = "-Atún enlatado al natural\n-Garbanzos cocidos\n-Tomate\n-Pepino\n-Cebolla roja\n-Aceite de oliva\n-Limón\n-Sal y pimienta\n-Perejil fresco picado",
                preparacion = "Enjuaga y escurre los garbanzos. Mezcla los garbanzos con el atún en un tazón. Agrega tomate, pepino y cebolla roja picados. Adereza la ensalada con aceite de oliva, jugo de limón, sal, pimienta y perejil fresco.",
                imagenC = R.drawable.ensalada_atun_garbanzos
            ),
            Comidas(
                nombre = "Filete de Ternera con Puré de Patatas y Espárragos al Horno",
                calorias = 650,
                ingredientes = "-Filete de ternera\n-Patatas\n-Mantequilla\n-Leche entera\n-Espárragos\n-Aceite de oliva\n-Sal y pimienta\n-Hierbas (tomillo, romero) para el filete",
                preparacion = "Precalienta el horno a 200°C. Hierve las patatas hasta que estén tiernas, luego haz un puré con mantequilla, leche, sal y pimienta. Sazona el filete de ternera con sal, pimienta y hierbas al gusto. Calienta una sartén con aceite de oliva y cocina el filete de ternera a la temperatura deseada. Mientras tanto, asa los espárragos en el horno con aceite de oliva, sal y pimienta. Sirve el filete con el puré de patatas y los espárragos asados.",
                imagenC = R.drawable.filete_ternera_esparragos_horno_pure_papas
            ),
            Comidas(
                nombre = "Salmón al Horno con Salsa de Crema de Limón y Arroz Integral",
                calorias = 750,
                ingredientes = "-Filete de salmón\n-Arroz integral\n-Crema de leche\n-Jugo de limón\n-Mantequilla\n-Sal y pimienta\n-Eneldo fresco (opcional)",
                preparacion = "Cocina el arroz integral según las instrucciones del paquete. Mientras se cocina el arroz, precalienta el horno a 200°C. Coloca el filete de salmón en una bandeja para horno y adereza con sal y pimienta. Hornea el salmón durante unos 15-20 minutos o hasta que esté cocido. En una cacerola, derrite mantequilla, agrega crema de leche y jugo de limón, y cocina a fuego lento hasta que la salsa se espese. Sirve el salmón con la salsa de crema de limón sobre el arroz integral, y decora con eneldo fresco si lo deseas.",
                imagenC = R.drawable.salmon_horno_salsa_limon_arrozointegral
            ),
            Comidas(
                nombre = "Pollo al Curry con Arroz Basmati y Naan",
                calorias = 850,
                ingredientes = "-Pechugas de pollo\n-Arroz Basmati\n-Salsa de curry\n-Leche de coco\n-Cebolla\n-Ajo\n-Jengibre\n-Aceite de cocina\n-Pan Naan",
                preparacion = "Cocina el arroz Basmati según las instrucciones del paquete. En una sartén grande, saltea la cebolla, el ajo y el jengibre en aceite de cocina. Agrega las pechugas de pollo y cocina hasta que estén doradas. Añade la salsa de curry y la leche de coco. Cocina a fuego lento hasta que el pollo esté cocido y la salsa se haya espesado. Sirve el pollo al curry con arroz Basmati y pan Naan.",
                imagenC = R.drawable.pollo_curry_arroz_naan
            )
        )
        return almuerzo
    }

    fun inicializarCena(): List<Comidas>{
        val cena = mutableListOf<Comidas>(
            Comidas(
                nombre = "Sopa de Calabacín",
                calorias = 125,
                ingredientes = "-Calabacines\n-Cebolla\n-Caldo de verduras bajo en sodio\n-Ajo\n-Aceite de oliva\n-Sal y pimienta\n-Hierbas al gusto (tomillo, albahaca)",
                preparacion = "En una olla grande, calienta un poco de aceite de oliva y saltea la cebolla y el ajo picados hasta que estén tiernos. Agrega los calabacines en rodajas y saltea por unos minutos. Vierte el caldo de verduras en la olla y lleva a ebullición. Cocina hasta que los calabacines estén tiernos. Usa una licuadora de mano o una licuadora de alta potencia para hacer un puré con la sopa. Añade sal, pimienta y hierbas al gusto. Sirve caliente.",
                imagenC = R.drawable.sopa_calabacin
            ),
            Comidas(
                nombre = "Pechuga de Pollo a la Parrilla con Brócoli al Vapor y Quinua",
                calorias = 325,
                ingredientes = "-Pechuga de pollo\n-Brócoli\n-Quinua\n-Aceite de oliva\n-Sal y pimienta\n-Limón (opcional)",
                preparacion = "Cocina la quinua según las instrucciones del paquete. Mientras se cocina la quinua, asa la pechuga de pollo a la parrilla con un poco de aceite de oliva, sal y pimienta. Cocina el brócoli al vapor o hiérvelo hasta que esté tierno. Sirve la pechuga de pollo con el brócoli y la quinua. Puedes añadir un poco de jugo de limón si lo deseas.",
                imagenC = R.drawable.pechuga_pollo_parrila_brocoli_quinua
            ),
            Comidas(
                nombre = "Ensalada de Salmón con Espárragos y Vinagreta",
                calorias = 275,
                ingredientes = "-Filete de salmón al horno (puedes utilizar salmón enlatado al natural)\n-Espárragos\n-Verduras de hojas verdes (lechuga, espinacas)\n-Tomate\n-Aceite de oliva\n-Vinagre balsámico\n-Mostaza\n-Sal y pimienta",
                preparacion = "Cocina el salmón al horno o utiliza salmón enlatado al natural. Cocina los espárragos al vapor o hiérvelos hasta que estén tiernos. Prepara una ensalada con verduras de hojas verdes, tomate y espárragos. Prepara una vinagreta baja en calorías mezclando aceite de oliva, vinagre balsámico, mostaza, sal y pimienta al gusto. Coloca el salmón sobre la ensalada y adereza con la vinagreta.",
                imagenC = R.drawable.ensalada_salmon_esparragos_vinagreta
            ),
            Comidas(
                nombre = "Pechuga de Pollo Rellena de Espinacas y Queso Feta",
                calorias = 425,
                ingredientes = "-Pechuga de pollo\n-Espinacas frescas\n-Queso feta desmenuzado\n-Ajo picado\n-Aceite de oliva\n-Sal y pimienta\n-Tomate cherry (opcional)",
                preparacion = "Precalienta el horno a 200°C. Prepara una mezcla de espinacas, queso feta, ajo picado, sal y pimienta. Corta una hendidura en el centro de cada pechuga de pollo y rellénala con la mezcla de espinacas y queso feta. Calienta una sartén apta para horno con aceite de oliva y dora las pechugas de pollo por ambos lados. Transfiere la sartén al horno y cocina hasta que el pollo esté cocido. Opcionalmente, sirve con tomates cherry alrededor.",
                imagenC = R.drawable.pechuga_pollo_espinacas_quesofeta
            ),
            Comidas(
                nombre = "Salmón al Horno con Salsa de Mostaza y Brócoli al Vapor",
                calorias = 425,
                ingredientes = "-Filete de salmón\n-Mostaza dijon\n-Miel (o edulcorante bajo en calorías)\n-Limón\n-Brócoli\n-Aceite de oliva\n-Sal y pimienta",
                preparacion = "Precalienta el horno a 200°C. Mezcla mostaza dijon, miel (o edulcorante bajo en calorías) y jugo de limón para hacer la salsa. Coloca el filete de salmón en una bandeja para horno, adereza con aceite de oliva, sal y pimienta. Baña el salmón con la salsa de mostaza y hornea hasta que esté cocido. Cocina el brócoli al vapor hasta que esté tierno. Sirve el salmón con el brócoli al vapor.",
                imagenC = R.drawable.salmon_horno_salsa_mostaza_brocoli_vapor
            ),
            Comidas(
                nombre = "Ensalada de Garbanzos y Pavo",
                calorias = 375,
                ingredientes = "-Pavo cocido y en tiras\n-Garbanzos cocidos\n-Tomate\n-Pepino\n-Cebolla roja\n-Pimiento\n-Aceite de oliva\n-Vinagre balsámico\n-Hierbas (albahaca, orégano)\n-Sal y pimienta",
                preparacion = "Prepara una ensalada con tiras de pavo cocido, garbanzos cocidos, tomate, pepino, cebolla roja y pimiento. Adereza la ensalada con aceite de oliva, vinagre balsámico, hierbas al gusto, sal y pimienta.",
                imagenC = R.drawable.ensalada_garbanzos_pavo
            ),
            Comidas(
                nombre = "Lomo de Res con Puré de Papas y Espárragos al Parmesano",
                calorias = 750,
                ingredientes = "-Lomo de res\n-Papas\n-Mantequilla\n-Leche entera\n-Espárragos\n-Aceite de oliva\n-Sal y pimienta\n-Queso parmesano",
                preparacion = "Precalienta el horno a 200°C. Hierve las papas hasta que estén tiernas, luego haz un puré con mantequilla, leche, sal y pimienta. Sazona el lomo de res con sal y pimienta. Calienta una sartén con aceite de oliva y cocina el lomo de res a la temperatura deseada. En otra bandeja para horno, coloca los espárragos, rocía con aceite de oliva, espolvorea queso parmesano y hornea hasta que estén tiernos. Sirve el lomo de res con el puré de papas y los espárragos al parmesano.",
                imagenC = R.drawable.lomo_res_pure_papas_esparragos_parmesano
            ),
            Comidas(
                nombre = "Salmón al Horno con Salsa de Mantequilla de Limón y Espárragos",
                calorias = 650,
                ingredientes = "-Filete de salmón\n-Mantequilla\n-Jugo de limón\n-Ajo picado\n-Espárragos\n-Aceite de oliva\n-Sal y pimienta",
                preparacion = "Precalienta el horno a 200°C. Derrite la mantequilla y mezcla con jugo de limón y ajo picado. Coloca el filete de salmón en una bandeja para horno y baña con la salsa de mantequilla de limón. Asa los espárragos con aceite de oliva, sal y pimienta en otra bandeja para horno. Hornea el salmón y los espárragos hasta que estén cocidos.",
                imagenC = R.drawable.salmon_horno_mantequilla_limon_esparragos
            ),
            Comidas(
                nombre = "Lasaña de Carne y Queso con Ensalada de Espinacas y Nueces",
                calorias = 850,
                ingredientes = "Lasaña:\n\n-Láminas de lasaña\n-Carne molida de res\n-Cebolla\n-Ajo\n-Tomate triturado enlatado\n-Queso ricotta\n-Queso mozzarella\n-Queso parmesano\n-Albahaca\n-Aceite de oliva\n-Sal y pimienta\nEnsalada:\n-Espinacas frescas\n-Nueces\n-Queso feta\n-Vinagre balsámico\n-Aceite de oliva\n-Sal y pimienta",
                preparacion = "Lasaña:\n\nEn una sartén grande, calienta aceite de oliva y saltea la cebolla y el ajo picados hasta que estén tiernos. Agrega la carne molida y cocina hasta que se dore. Añade el tomate triturado y la albahaca. Cocina a fuego lento durante unos minutos y sazona con sal y pimienta. En una fuente para horno, alterna capas de láminas de lasaña, carne, queso ricotta, queso mozzarella y queso parmesano.\n Hornea la lasaña a 180°C durante unos 30-40 minutos o hasta que esté burbujeante y dorada.\n\nEnsalada:\n\nPrepara una ensalada con espinacas frescas, nueces, y queso feta desmenuzado. Adereza con vinagre balsámico, aceite de oliva, sal y pimienta al gusto.",
                imagenC = R.drawable.lasanha_carne_queso_ensalada_espinacas_nueces
            ),
        )
        return cena
    }
    companion object{
        val CLAVE_LISTA = "clave_lista"
    }

}