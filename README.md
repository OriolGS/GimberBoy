# README – Arquitectura, Responsabilitats i Flux del Joc GimberPang

## 📦 1. Arquitectura general (MVC + Patrons utilitzats)

El projecte segueix un disseny basat en:

- **MVC** (Model – Vista – Controlador)
- **Observer** (la vista s'actualitza quan el model canvia)
- **Strategy** (cada sprite té la seva pròpia estratègia d'animació i dibuix)
- **Singleton** (Model i GestorDeDibuix tenen una única instància)

L'estructura per paquets és:
```
model/
   ListModelDeJoc  (Singleton, Observable)
vista/
   VistaDeJoc      (JFrame)
   ZonaDeJoc       (JPanel, Observer)
controlador/
   ControladorDeJoc
   GestorDeDibuix  (Singleton)
sprite/
   Sprite (abstract)
   GimberBoy, Bullet, Missil, EsferaL, EsferaR, NauEnemiga, Marcador, Fons...
```

## 🧩 2. Responsabilitats de cada classe

### 🧠 MODEL

#### IModelDeJoc

Interfície del model. Defineix el "contracte" del joc:

- `iniciarJoc()`
- `animarJoc()`
- `gestionarColisions()`

#### ListModelDeJoc

- **Rol**: cervell del joc.
- **Patrons**: Singleton + Observable.
- **Amb qui es comunica**:
  - Rep instruccions del `ControladorDeJoc`
  - Notifica canvis a `ZonaDeJoc` (Observer)
  - Utilitza el `GestorDeDibuix` per pintar els sprites
  - Gestiona tots els Sprites

- **Responsabilitats**:
  - Emmagatzemar i gestionar la `LinkedList` de Sprites (fons, HUD, protagonista, enemics, projectils…).
  - Actualitzar el joc a cada frame:
    - Recórrer els sprites
    - Cridar `animar()` de cada un (patró Strategy)
    - Detectar col·lisions
    - Afegir / eliminar sprites segons calgui
  - Gestionar l'estat del joc: pauses, vides, fi de partida, puntuacions.
  - Notificar la vista amb `setChanged()` + `notifyObservers()`.

### 🎨 VISTA

#### VistaDeJoc

- **Rol**: finestra principal del joc (JFrame).
- **Responsabilitats**:
  - Crear la interfície principal.
  - Conté el `main()`.
  - Afegir `ZonaDeJoc` al centre.
  - Crear el controlador i engegar el joc.

**Nota**: no conté lògica del joc ni de dibuix.

#### ZonaDeJoc

- **Rol**: zona visible del joc (canvas).
- **Patrons**: Observer.
- **Amb qui es comunica**:
  - Observa `ListModelDeJoc`
  - Demana les imatges al `GestorDeDibuix`
  - Envia events de ratolí al `ControladorDeJoc`

- **Responsabilitats**:
  - Dibuixar tots els sprites a cada `paintComponent()`.
  - Rebre notificacions del model i cridar `repaint()`.
  - Gestionar el doble buffer.
  - Capturar coordenades i clics del ratolí.

### 🕹 CONTROLADOR

#### ControladorDeJoc

- **Rol**: el director d'orquestra.
- **Patrons**: MVC + Game Loop.
- **Amb qui es comunica**:
  - Envia inputs al `ListModelDeJoc`
  - Gestiona posició del ratolí i clics
  - Controla el bucle del joc
  - Interactua amb la vista (`ZonaDeJoc`) només per inputs

- **Responsabilitats**:
  - Bucle principal:
    - `model.animarJoc()`
    - `notifyObservers()` perquè la vista es pinti
    - Delay temporal
  - Processar inputs del jugador:
    - `mouseMoved()` → actualitza posició de GimberBoy al model
    - `mousePressed()` → dispara (`hemPitjatElMouse()`)

#### GestorDeDibuix

- **Rol**: gestor gràfic centralitzat.
- **Patró**: Singleton.
- **Responsabilitats**:
  - Carregar i mantenir les imatges en un `HashMap`.
  - Proporcionar funcions simples per dibuixar: `pintarImatge(clau, x, y)`
  - Garantir que la Vista no hagi de saber d'on provenen les imatges.
  - Evitar recàrregues innecessàries d'imatges.

### 🧱 SPRITES (paquet sprite/)

#### Sprite (abstracta)

- **Patrons**: Strategy (cada sprite té el seu propi animar/pintar).
- **Responsabilitats**:
  - Variables bàsiques: x, y, amplada, alçada, vida…
  - Mètodes abstractes:
    - `animar()`
    - `pintar(Graphics g)`
  - Defineix el comportament comú dels elements del joc.

#### Sprites Concrets

Cadascun implementa la seva pròpia estratègia de moviment i dibuix.

**Fons**
- Es pinta primer.
- No es mou.

**Marcador**
- Mostra puntuació i objectius destruïts.
- No té interacció física.

**GimberBoy**
- Protagonista.
- Es mou seguint el ratolí només a la part baixa de la pantalla.
- 3 vides.
- Pot disparar `Bullet`.

**Bullet**
- Neix a la posició del protagonista.
- Puja verticalment.
- Destrueix enemics quan col·lisiona.

**EsferaL**
- Apareix part superior esquerra.
- Es mou cap a la dreta i rebota.
- Velocitat 2 px/torn.
- Té 2 vides.

**EsferaR**
- Apareix a la dreta i es mou cap a l'esquerra.
- Velocitat 4 px/torn.
- Té 1 vida.

**NauEnemiga**
- Posició inicial aleatòria.
- Pot desaparèixer i reaparèixer.
- Dispara `Missil`.
- 3 vides.
- Opcional: pot moure's aleatòriament uns torns.

**Missil**
- Disparat per la nau enemiga.
- Baixa 2 px/torn.
- Pot destruir GimberBoy i també els Bullet.

## 🔄 3. Flux complet del joc (pas a pas)

A continuació tens el flux cronològic des del main fins a cada frame del joc.

### 1️⃣ Arrencada

**VistaDeJoc.main()**

- Es crea la finestra del joc.
- S'afegeix `ZonaDeJoc`.
- Es crea `ControladorDeJoc`.
- Es crida `jugar()`.

### 2️⃣ El controlador engega el joc

**ControladorDeJoc.jugar()**

- `model.iniciarJoc()`
  - El model crea els sprites inicials:
    - Fons
    - GimberBoy
    - Enemics inicials
    - Marcador
- Comença el bucle de joc infinit:
```java
while(true) {
    model.animarJoc();
    model.notifyObservers();  // provoquen repaint a ZonaDeJoc
}
```

### 3️⃣ Actualització del model (cada frame)

**ListModelDeJoc.animarJoc()**

- Recorre la llista de sprites amb `Iterator`.
- Per a cada sprite → `sprite.animar()`
- Gestiona col·lisions i eliminació/destrucció
- Ajusta estats: vides, game over, puntuació.
- Crida `setChanged()`
- El controlador fa `notifyObservers()` → informa la vista

### 4️⃣ La vista rep la notificació

**ZonaDeJoc.update()**

- Rep avís del model
- Executa `repaint()`

### 5️⃣ La vista es torna a pintar

**ZonaDeJoc.paintComponent()**

- Esborra pantalla
- `ListModelDeJoc.getInstancia().pintarJoc()`
  - Recorre la llista de sprites
  - Crida `sprite.pintar()`
  - Aquest utiliza `GestorDeDibuix` per obtenir les imatges
- Dibuixa el buffer final a pantalla

### 6️⃣ Entrada del jugador

**Ratolí**

- `mouseMoved()` → actualitza posició de GimberBoy al model
- `mousePressed()` → el model crea un `Bullet`
- Tot provoca notificacions i el cicle es repeteix.
