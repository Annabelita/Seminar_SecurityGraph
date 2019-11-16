import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Graph {

    private static List<Vertex> vertexList = new ArrayList<Vertex>();
    private static List<Vertex> leafList = new ArrayList<Vertex>();
    private static List<Vertex> leafListMobileApp = new ArrayList<Vertex>();
    private static List<Vertex> leafListWebApp = new ArrayList<Vertex>();
    private static List<Vertex> leafListDesktopApp = new ArrayList<Vertex>();
    HashMap<Vertex, Integer> valueList = new HashMap<>();

    private static Map<Integer, List<Vertex>> adjVertices = new HashMap<>();
    public static Map<Vertex, Integer> bestPath = new HashMap<>();
    private int platformFlag;


    Vertex abbruch = new Vertex(-100);


    private Vertex addVertex(Vertex v) {
        ArrayList<Vertex> empty = new ArrayList<Vertex>();
        vertexList.add(v);
        adjVertices.putIfAbsent(v.id, empty);

        return v;
    }


    private void removeVertex(Vertex v) {
        vertexList.remove(v);
        adjVertices.remove(v.id);
    }



    /**
     * Returns a list containing all vertices in the graph
     * @return
     */

    private static List<Vertex> getAllVertices(){
        return vertexList;
    }


    /**
     * Adds an edge between v1 and v2
     * @param v1
     * @param v2
     */

    private void addEdge(Vertex v1, Vertex v2) {
        adjVertices.get(v1.getId()).add(v2);
        adjVertices.get(v2.getId()).add(v1);
    }


    private void removeEdge(Vertex v) {
        //adjVertices.values().removeAll(Collections.singleton(v));

        for(Map.Entry<Integer, List<Vertex>> entry : adjVertices.entrySet()) {
            Integer key = entry.getKey();
            List<Vertex> value = entry.getValue();

            for(int i = 0; i < value.size(); i++){
                if(value.get(i) == v){
                    value.remove(v);
                }
            }
        }
    }


    /**
     * Setup of Game Environment
     * @return
     */

    private static Graph createGraph() {
        Graph graph = new Graph();



        /**
         *  alle inneren Knoten
         */

        Vertex v0 = new Vertex(0);
        v0.parent = null;
        v0.setLabel("Start");
        v0.setCost(0);
        v0.setNodeValue(0);
        graph.addVertex(v0);



        Vertex v1 = new Vertex(1);
        v1.parent = v0;
        v0.children.add(v1);
        v1.setLabel("MobileApp");
        v1.setCost(0);
        v1.setNodeValue(0);
        graph.addVertex(v1);


        Vertex v2 = new Vertex(2);
        v2.parent = v0;
        v0.children.add(v2);
        v2.setLabel("BrowserApp");
        v2.setCost(0);
        v2.setNodeValue(0);
        graph.addVertex(v2);


        Vertex v3 = new Vertex(3);
        v3.parent = v0;
        v0.children.add(v3);
        v3.setLabel("WebApp");
        v3.setCost(0);
        v3.setNodeValue(0);
        graph.addVertex(v3);


        Vertex v4 = new Vertex(4);
        v4.parent = v1;
        v1.children.add(v4);
        v4.setLabel("Information Disclosure MobileApp");
        v4.setCost(4);
        v4.setNodeValue(0);
        graph.addVertex(v4);


        Vertex v5 = new Vertex(5);
        v5.parent = v4;
        v4.children.add(v5);
        v5.setLabel("Mitlesen von Fehlermeldungen in der App");
        v5.setCost(2);
        v5.setNodeValue(0);
        graph.addVertex(v5);


        Vertex v6 = new Vertex(6);
        v6.parent = v5;
        v5.children.add(v6);
        v6.setLabel("adb logcat");
        v6.setCost(3);
        v6.setNodeValue(0);
        graph.addVertex(v6);


        Vertex v7 = new Vertex(7);
        v7.parent = v1;
        v1.children.add(v7);
        v7.setLabel("Kryptographie MobileApp");
        v7.setCost(2);
        v7.setNodeValue(0);
        graph.addVertex(v7);



        Vertex v8 = new Vertex(8);
        v8.parent = v1;
        v1.children.add(v8);
        v8.setLabel("Unsicherer Export");
        v8.setCost(2);
        v8.setNodeValue(0);
        graph.addVertex(v8);


        Vertex v9 = new Vertex(9);
        v9.parent = v1;
        v1.children.add(v9);
        v9.setLabel("Input Validation");
        v9.setCost(5);
        v9.setNodeValue(0);
        graph.addVertex(v9);


        Vertex v10 = new Vertex(10);
        v10.parent = v9;
        v9.children.add(v10);
        v10.setLabel("Einbetten von HTML Code in WebView");
        v10.setCost(4);
        v10.setNodeValue(0);
        graph.addVertex(v10);


        Vertex v11 = new Vertex(11);
        v11.parent = v10;
        v10.children.add(v11);
        v11.setLabel("Pishing");
        v11.setCost(2);
        v11.setNodeValue(0);
        graph.addVertex(v11);


        Vertex v12 = new Vertex(12);
        v12.parent = v2;
        v2.children.add(v12);
        v12.setLabel("Kryptographie Browser");
        v12.setCost(1);
        v12.setNodeValue(0);
        graph.addVertex(v12);


        Vertex v13 = new Vertex(13);
        v13.parent = v2;
        v2.children.add(v13);
        v13.setLabel("XSS");
        v13.setCost(8);
        v13.setNodeValue(0);
        graph.addVertex(v13);


        Vertex v14 = new Vertex(14);
        v14.parent = v13;
        v13.children.add(v14);
        v14.setLabel("Schadhafter Payload");
        v14.setCost(6);
        v14.setNodeValue(0);
        graph.addVertex(v14);


        /*
        Vertex v15 = new Vertex(15);
        v15.parent = v14;
        v15.setLabel("HTTP Query");
        v15.setCost(5);
        v15.setNodeValue(0);
        graph.addVertex(v15);

         */


        Vertex v16 = new Vertex(16);
        v16.parent = v14;
        v14.children.add(v16);
        v16.setLabel("HTTP Request");
        v16.setCost(5);
        v16.setNodeValue(0);
        graph.addVertex(v16);


        /*
        Vertex v17 = new Vertex(17);
        v17.parent = v14;
        v17.setLabel("HTML Code");
        v17.setCost(5);
        v17.setNodeValue(0);
        graph.addVertex(v17);

         */


        Vertex v18 = new Vertex(18);
        v18.parent = v16;
        v16.children.add(v18);
        v18.setLabel("Session Hijacking");
        v18.setCost(3);
        v18.setNodeValue(0);
        graph.addVertex(v18);


        Vertex v19 = new Vertex(19);
        v19.parent = v2;
        v2.children.add(v19);
        v19.setLabel("Transport Security");
        v19.setCost(6);
        v19.setNodeValue(0);
        graph.addVertex(v19);


        Vertex v20 = new Vertex(20);
        v20.parent = v19;
        v19.children.add(v20);
        v20.setLabel("Man-in-the-Middle");
        v20.setCost(5);
        v20.setNodeValue(0);
        graph.addVertex(v20);


        Vertex v21 = new Vertex(21);
        v21.parent = v3;
        v3.children.add(v21);
        v21.setLabel("Information Disclosure WebApp");
        v21.setCost(3);
        v21.setNodeValue(0);
        graph.addVertex(v21);


        Vertex v22 = new Vertex(22);
        v22.parent = v21;
        v21.children.add(v22);
        v22.setLabel("Brute Force Email & Passwort");
        v22.setCost(1);
        v22.setNodeValue(0);
        graph.addVertex(v22);


        Vertex v23 = new Vertex(23);
        v23.parent = v3;
        v3.children.add(v23);
        v23.setCost(2);
        v23.setLabel("Authentifizierung");
        v23.setNodeValue(0);
        graph.addVertex(v23);


        Vertex v24 = new Vertex(24);
        v24.parent = v3;
        v3.children.add(v24);
        v24.setCost(10);
        v24.setLabel("Kryptographie WebApp");
        v24.setNodeValue(0);
        graph.addVertex(v24);


        Vertex v25 = new Vertex(25);
        v25.parent = v24;
        v24.children.add(v25);
        v25.setCost(3);
        v25.setLabel("Überschreiben des Public Key");
        v25.setNodeValue(0);
        graph.addVertex(v25);


        Vertex v26 = new Vertex(26);
        v26.parent = v24;
        v24.children.add(v26);
        v26.setCost(5);
        v26.setLabel("Preisgabe von Dokumenten & Metadaten");
        v26.setNodeValue(0);
        graph.addVertex(v26);

/*
        Vertex v27 = new Vertex(27);
        v27.parent = v26
        v27.setCost(4);
        v27.setLabel("HTTP Reffer");
        v27.setNodeValue(0);
        graph.addVertex(v27);

 */


        Vertex v28 = new Vertex(28);
        v28.parent = v26;
        v26.children.add(v28);
        v28.setCost(4);
        v28.setLabel("HTTP Request WebApp");
        v28.setNodeValue(0);
        graph.addVertex(v28);


        /*
        Vertex v29 = new Vertex(29);
        v29.setCost(4);
        v29.setLabel("Websocket");
        v29.setNodeValue(0);
        graph.addVertex(v29);

         */



        Vertex v30 = new Vertex(30);
        v30.parent = v28;
        v28.children.add(v30);
        v30.setCost(1);
        v30.setLabel("Brute Force PIN");
        v30.setNodeValue(0);
        graph.addVertex(v30);


        Vertex v31 = new Vertex(31);
        v31.parent = v30;
        v30.children.add(v31);
        v31.setCost(2);
        v31.setLabel("HTTP Put");
        v31.setNodeValue(0);
        graph.addVertex(v31);


        Vertex v32 = new Vertex(32);
        v32.parent = v31;
        v31.children.add(v32);
        v32.setCost(1);
        v32.setLabel("Entschlüsseln von Daten");
        v32.setNodeValue(0);
        graph.addVertex(v32);


        /**
         *  Alle Blätter
         */

        Vertex v33 = new Vertex(33);
        v33.parent = v6;
        v6.children.add(v33);
        v33.setLabel("Mitlesen fehlgeschlagen");
        v33.setCost(1);
        v33.setNodeValue(-2);
        graph.addVertex(v33);
        leafList.add(v33);


        Vertex v34 = new Vertex(34);
        v34.parent = v6;
        v6.children.add(v34);
        v34.setLabel("Mitlesen personenbezogener Daten");
        v34.setCost(1);
        v34.setNodeValue(5);
        graph.addVertex(v34);
        leafList.add(v34);


        Vertex v35 = new Vertex(35);
        v35.parent = v8;
        v8.children.add(v35);
        v35.setLabel("Nichts exportiert - Fail!");
        v35.setCost(1);
        v35.setNodeValue(-2);
        graph.addVertex(v35);
        leafList.add(v35);


        Vertex v36 = new Vertex(36);
        v36.parent = v8;
        v8.children.add(v36);
        v36.setLabel("Private Key erfolgreich ausgelesen");
        v36.setCost(1);
        v36.setNodeValue(9);
        graph.addVertex(v36);
        leafList.add(v36);

        Vertex v37 = new Vertex(37);
        v37.parent = v10;
        v10.children.add(v37);
        v37.setLabel("Daten überschrieben");
        v37.setCost(1);
        v37.setNodeValue(7);
        graph.addVertex(v37);
        leafList.add(v37);

        Vertex v38 = new Vertex(38);
        v38.parent = v11;
        v11.children.add(v38);
        v38.setLabel("Passwort & Email ausgelesen");
        v38.setCost(1);
        v38.setNodeValue(8);
        graph.addVertex(v38);
        leafList.add(v38);

        Vertex v39 = new Vertex(39);
        v39.parent = v11;
        v11.children.add(v39);
        v39.setLabel("TOTP Code ausgelesen");
        v39.setCost(1);
        v39.setNodeValue(6);
        graph.addVertex(v39);
        leafList.add(v39);


        Vertex v40 = new Vertex(40);
        v40.parent = v12;
        v12.children.add(v40);
        v40.setLabel("Daten Verschlüsselt");
        v40.setCost(1);
        v40.setNodeValue(7);
        graph.addVertex(v40);
        leafList.add(v40);



        Vertex v41 = new Vertex(41);
        v41.parent = v13;
        v13.children.add(v41);
        v41.setLabel("Private Key aus Browser App ausgelesen");
        v41.setCost(1);
        v41.setNodeValue(9);
        graph.addVertex(v41);
        leafList.add(v41);


        Vertex v42 = new Vertex(42);
        v42.parent = v16;
        v16.children.add(v42);
        v42.setLabel("Schadhaften Code in WebApp eingebettet");
        v42.setCost(1);
        v42.setNodeValue(6);
        graph.addVertex(v42);
        leafList.add(v42);


        Vertex v43 = new Vertex(43);
        v43.parent = v16;
        v16.children.add(v43);
        v43.setLabel("Defacement von Inhalten");
        v43.setCost(1);
        v43.setNodeValue(6);
        graph.addVertex(v43);
        leafList.add(v43);


        Vertex v44 = new Vertex(44);
        v44.parent = v18;
        v18.children.add(v44);
        v44.setLabel("LocaleStorage ausgelesen");
        v44.setCost(1);
        v44.setNodeValue(8);
        graph.addVertex(v44);
        leafList.add(v44);


        Vertex v45 = new Vertex(45);
        v45.parent = v18;
        v18.children.add(v45);
        v45.setLabel("personenbezogene Daten ausgelesen");
        v45.setCost(1);
        v45.setNodeValue(5);
        graph.addVertex(v45);
        leafList.add(v45);


        Vertex v46 = new Vertex(46);
        v46.parent = v20;
        v20.children.add(v46);
        v46.setLabel("Daten manipuliert");
        v46.setCost(1);
        v46.setNodeValue(45);
        graph.addVertex(v46);
        leafList.add(v46);


        Vertex v47 = new Vertex(47);
        v47.parent = v20;
        v20.children.add(v47);
        v47.setLabel("Daten heimlich mitgelesen");
        v47.setCost(1);
        v47.setNodeValue(3);
        graph.addVertex(v47);
        leafList.add(v47);


        Vertex v48 = new Vertex(48);
        v48.parent = v20;
        v20.children.add(v48);
        v48.setCost(1);
        v48.setLabel("Man-in-the-Middle Proxy eingefügt");
        v48.setNodeValue(7);
        graph.addVertex(v48);
        leafList.add(v48);


        Vertex v49 = new Vertex(49);
        v49.parent = v20;
        v20.children.add(v49);
        v49.setLabel("Schadhafte Links eingefügt");
        v49.setCost(1);
        v49.setNodeValue(6);
        graph.addVertex(v49);
        leafList.add(v49);



        Vertex v50 = new Vertex(50);
        v50.parent = v20;
        v20.children.add(v50);
        v50.setLabel("Auf unverschlüsselte vivy.com Seite weitergeleitet");
        v50.setCost(1);
        v50.setNodeValue(6);
        graph.addVertex(v50);
        leafList.add(v50);

        Vertex v51 = new Vertex(51);
        v51.parent = v22;
        v22.children.add(v51);
        v51.setLabel("Handynummer ausgelesen");
        v51.setCost(1);
        v51.setNodeValue(6);
        graph.addVertex(v51);
        leafList.add(v51);


        Vertex v52 = new Vertex(52);
        v52.parent = v21;
        v21.children.add(v52);
        v52.setLabel("Email & Passwort geprüft");
        v52.setCost(1);
        v52.setNodeValue(3);
        graph.addVertex(v52);
        leafList.add(v52);

        Vertex v53 = new Vertex(53);
        v53.parent = v23;
        v23.children.add(v53);
        v53.setLabel("Erfolglose Email Authentifizierung");
        v53.setCost(1);
        v53.setNodeValue(-2);
        graph.addVertex(v53);
        leafList.add(v53);


        Vertex v54 = new Vertex(54);
        v54.parent = v23;
        v23.children.add(v54);
        v54.setLabel("Brute Force Email & Passwort erfolgreich");
        v54.setCost(1);
        v54.setNodeValue(7);
        graph.addVertex(v54);
        leafList.add(v54);

        Vertex v55 = new Vertex(55);
        v55.parent = v24;
        v24.children.add(v55);
        v55.setLabel("Dokumente verschlüsselt");
        v55.setCost(1);
        v55.setNodeValue(7);
        graph.addVertex(v55);
        leafList.add(v55);


        Vertex v56 = new Vertex(56);
        v56.parent = v25;
        v25.children.add(v56);
        v56.setLabel("Mitlesen von Dokumentenaustausch");
        v56.setCost(1);
        v56.setNodeValue(5);
        graph.addVertex(v56);
        leafList.add(v56);


        Vertex v57 = new Vertex(57);
        v57.parent = v25;
        v25.children.add(v57);
        v57.setLabel("Daten überschrieben");
        v57.setCost(1);
        v57.setNodeValue(6);
        graph.addVertex(v57);
        leafList.add(v57);


        Vertex v58 = new Vertex(58);
        v58.parent = v25;
        v25.children.add(v58);
        v58.setLabel("Daten verschlüsselt & Opfer erpresst");
        v58.setCost(1);
        v58.setNodeValue(10);
        graph.addVertex(v58);
        leafList.add(v58);

        Vertex v59 = new Vertex(59);
        v59.parent = v30;
        v30.children.add(v59);
        v59.setLabel("Bereits aufgerufenes Dokument gelesen");
        v59.setCost(1);
        v59.setNodeValue(5);
        graph.addVertex(v59);
        leafList.add(v59);


        Vertex v60 = new Vertex(60);
        v60.parent = v32;
        v32.children.add(v60);
        v60.setLabel("Noch nicht aufgerufenes Dokument entschlüsselt & gelesen");
        v60.setCost(1);
        v60.setNodeValue(5);
        graph.addVertex(v60);
        leafList.add(v60);


        Vertex v61 = new Vertex(61);
        v61.parent = v7;
        v7.children.add(v61);
        v61.setLabel("Verschlüsselung von Dokumenten & Erpressung");
        v61.setCost(1);
        v61.setNodeValue(10);
        graph.addVertex(v61);
        leafList.add(v61);


        Vertex v62 = new Vertex(62);
        v62.parent = v7;
        v7.children.add(v62);
        v62.setLabel("Public & Privat Key ausgetauscht");
        v62.setCost(1);
        v62.setNodeValue(10);
        graph.addVertex(v62);
        leafList.add(v62);



        /**
         *  Edges
         */

        graph.addEdge(v0, v1);
        graph.addEdge(v0, v2);
        graph.addEdge(v0, v3);

        graph.addEdge(v1, v4);
        graph.addEdge(v1, v7);
        graph.addEdge(v1, v8);
        graph.addEdge(v1, v9);


        graph.addEdge(v4, v5);
        graph.addEdge(v5, v6);
        graph.addEdge(v6, v33);
        graph.addEdge(v6, v34);


        graph.addEdge(v7, v62);
        graph.addEdge(v7, v61);

        graph.addEdge(v8, v35);
        graph.addEdge(v8, v36);

        graph.addEdge(v9, v10);

        graph.addEdge(v10, v37);
        graph.addEdge(v10, v11);

        graph.addEdge(v11, v38);
        graph.addEdge(v11, v39);

        graph.addEdge(v2, v12);
        graph.addEdge(v12, v40);

        graph.addEdge(v2, v13);
        graph.addEdge(v13, v14);
        //graph.addEdge(v14, v15);
        graph.addEdge(v14, v16);
        //graph.addEdge(v14, v17);

        //graph.addEdge(v15, v18);
        graph.addEdge(v16, v18);
        graph.addEdge(v16, v18);
        graph.addEdge(v13, v41);

        //graph.addEdge(v15, v42);
        graph.addEdge(v16, v42);
        //graph.addEdge(v17, v42);
        //graph.addEdge(v15, v43);
        graph.addEdge(v16, v43);
        //graph.addEdge(v17, v43);

        graph.addEdge(v18, v44);
        graph.addEdge(v18, v45);

        graph.addEdge(v19, v20);
        graph.addEdge(v20, v50);
        graph.addEdge(v20, v49);
        graph.addEdge(v20, v48);
        graph.addEdge(v20, v47);
        graph.addEdge(v20, v46);

        graph.addEdge(v3, v24);
        graph.addEdge(v24, v25);
        graph.addEdge(v25, v57);
        graph.addEdge(v25, v56);
        graph.addEdge(v25, v58);
        graph.addEdge(v24, v55);
        graph.addEdge(v24, v26);
        //graph.addEdge(v26, v27);
        graph.addEdge(v26, v28);
        //graph.addEdge(v26, v29);
        //graph.addEdge(v27, v30);
        graph.addEdge(v28, v30);
        //graph.addEdge(v29, v30);
        graph.addEdge(v30, v59);
        graph.addEdge(v30, v31);
        graph.addEdge(v31, v32);
        graph.addEdge(v32, v60);

        graph.addEdge(v3, v23);
        graph.addEdge(v23, v53);
        graph.addEdge(v23, v54);

        graph.addEdge(v3, v21);
        graph.addEdge(v21, v22);
        graph.addEdge(v21, v52);
        graph.addEdge(v22, v51);

        return graph;
    }


    /**
     * Returns the vertex with ID = n
     * @param n the ID of the Vertex
     * @return
     */
    private static Vertex getNodeById(int n){
        List<Vertex> allNodes = getAllVertices();
        for(Vertex v : allNodes){
            if(v.getId() == n)
                return v;
        }
        throw new RuntimeException("BAD ID: This id doesn't exist in the System!");
    }


    /**
     * Chooses n random elements from all vertices
     * @param n
     * @return
     */

    public static List<Vertex> chooseRandomToProtect(int n){
        List<Vertex> protectedVertices = new ArrayList<Vertex>();
        Collections.shuffle(leafList);

        if(n > leafList.size())
            n = leafList.size()-1;

        for(int i = 0; i < n; i++){
            protectedVertices.add(leafList.get(i));
        }
        return  protectedVertices;
    }


    /**
     * Chooses n random elements from either Platform 1, 2 or 3
     * 1: MobileApp
     * 2: DesktopApp
     * 3: WebApp
     * @param n
     * @return a list of random choosen vertexes
     */
    public List<Vertex> chooseRandomSmartToProtect(int n){
        List<Vertex> protectedVertices = new ArrayList<Vertex>();
        List<Vertex> leafs = Arrays.asList();

        switch(platformFlag){
            case 1:
                leafs = leafListMobileApp;
            case 2:
                leafs = leafListDesktopApp;
            case 3:
                leafs = leafListWebApp;
        }
        Collections.shuffle(leafs);

        if(n > leafs.size())
            n = leafs.size()-1;

        for(int i = 0; i < n; i++)
            protectedVertices.add(leafs.get(i));

        return  protectedVertices;
    }



    /**
     * Returns a list of adjacent vertexes given a start node
     * @param start
     * @return List of neighbor vertexes of start
     */

    public static List<Vertex> getAdjacentNodes(Vertex start){
        List<Vertex> neighbors = new ArrayList<Vertex>();

        for(Map.Entry<Integer, List<Vertex>> entry : adjVertices.entrySet()) {
            Integer key = entry.getKey();
            List<Vertex> n = entry.getValue();

            if(key == start.getId()) {
                neighbors = n;
                break;
            }
        }
        return neighbors;
    }


    /**
     * Checks if the defender could successfully protect a leaf
     * @param attackedNodes
     * @param protectedNodes
     * @return true, if leaf was protected by defender
     */

    public static boolean checkIfDefended(Vertex attackedNodes, List<Vertex> protectedNodes){
        if(protectedNodes.contains(attackedNodes))
            return true;
        else
            return false;

    }

    /**
     * Checks, if vertex v is a leaf or a inner node
     * @param v
     * @return true, if v is a leaf, else false
     */

    public static boolean isLeaf(Vertex v){
        if(leafList.contains(v))
            return true;
        return false;
    }


    /**
     * Randomly choose a platform (WebApp, MobileApp or DesktopApp)
     * Sets platformFlag
     */

    public void choosePlatform(){
            Random rand = new Random();
            List<Vertex> platform = new ArrayList<Vertex>(Arrays.asList(vertexList.get(1),vertexList.get(2),vertexList.get(3)));
            Vertex randomPlatform = platform.get(rand.nextInt(platform.size()));

            switch(randomPlatform.id){
                case 1:
                    platformFlag = 1;
                case 2:
                    platformFlag = 2;
                case 3:
                    platformFlag = 3;
            }
    }



    /**
     * Randomly chooses the next move given a start vertex
     * @param start
     * @return
     */
    public Vertex attackerChooseMoveRANDOM(Vertex start){
        if(start.id == 0)
            choosePlatform();

        if(isLeaf(start))
            return vertexList.get(platformFlag);


        List<Vertex> neighbors = getAdjacentNodes(start);
        List<Vertex> validNeighbors = new ArrayList<>();
        for(Vertex tmp : neighbors){
            if(tmp.id < start.id)
                continue;
            validNeighbors.add(tmp);
        }

        if(validNeighbors.isEmpty())
            return vertexList.get(0);

        Random rand = new Random();
        return validNeighbors.get(rand.nextInt(validNeighbors.size()));
    }


    /**
     * Deletes the parent of a vertex rekursivly
     * @param vertex
     * @return
     */

    public Vertex deletePath(Vertex vertex){
        try {
            Vertex parent = vertex.parent;
            //if(parent == null)
            //    return vertex;

            if (parent.children.contains(vertex))
                parent.children.remove(vertex);

            if (parent.children.size() < 1) {
                if (parent.children != null) {
                    parent.children.clear();
                }
                return deletePath(parent);

            } else {
                return parent;
            }
        } catch (NullPointerException e) {
            return abbruch;
        }
    }


    /**
     * Given a current Vertex, chooses the neighbor with a path to the most valuable leaf child
     * @param start
     * @return
     */

    public Vertex forwardPropagateGreedy(Vertex start){

        /**
         * 1. Check if startnode
         * If yes, then randomly choose a platform and return the root platform vertex
         */
        if(start.id == 0) {
            choosePlatform();
            return vertexList.get(platformFlag);
        }

        if(start.id == 1 || start.id == 2 || start.id == 3){
            if(start.children.isEmpty()) {
                if(start.id == 1)
                    platformFlag = 2;
                else if(start.id == 2)
                    platformFlag = 3;
                else
                    platformFlag = 1;
                return vertexList.get(platformFlag);
            }

        }


        List<Vertex> neighbors = start.children;

        if(isLeaf(start) && start.count > 0) {
            Vertex neu = deletePath(start);
            if(neu.id == -100){
                return neu;
            }
            return forwardPropagateGreedy(neu);
        }


        for(int i = 0; i < neighbors.size(); i ++) {
            if(isLeaf(neighbors.get(i))){
                return neighbors.get(i);
            }


            Vertex tmp = neighbors.get(i);
            Vertex child = findChild(tmp);
            valueList.put(tmp, child.nodeValue);


            try {
                Vertex best = Collections.max(valueList.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
                if(isLeaf(best))
                    best.count++;
                valueList.clear();
                return best;

            } catch (NoSuchElementException e){
                throw new RuntimeException("NoSuchElement happened with " + start);
            }

        }
        return start;
    }



    /**
     * Recursive function that finds a child of a root node
     * @param current -- root
     * @return Child vertex
     */

    private Vertex findChild(Vertex current) {
        if(isLeaf(current)) {
            return current;
        }

        List<Vertex> children = current.children;
        for (Vertex child : children) {
            if(child.children == null)
                continue;

            if(child.children.isEmpty())
                continue;
            else
                findChild(child);
        }
        return current;
    }


    /**
     * Creates 3 leaf lists (one for each platform)
     * @param factor
     */

    public void setLeafValues(int factor){
        for(Vertex v : vertexList){
            if(isLeaf(v)) {
                v.setNodeValue(v.getNodeValue() * factor);

                if(Arrays.asList(33, 34, 62, 61, 35, 36, 37, 38, 39).contains(v.id))
                    leafListMobileApp.add(v);
                else if(Arrays.asList(40, 41, 42, 43, 44, 45, 50, 49, 48, 47, 46).contains(v.id))
                    leafListDesktopApp.add(v);
                else
                    leafListWebApp.add(v);
            }
        }
    }


    /**
     * Simulation Function
     * Params must be set in line 1131
     * @param algorithmn
     * @param logPath
     * @throws IOException
     */
    public void simulate(String algorithmn, String logPath) throws IOException {

        boolean append = false;
        FileHandler handler = new FileHandler(logPath, append);

        Logger logger = Logger.getLogger(logPath);
        logger.addHandler(handler);

        handler.setFormatter(new CreateCustomFormatter.MyCustomFormatter());
        logger.setUseParentHandlers(false);

        for(Vertex v : getAllVertices()){
            if(isLeaf(v))
                v.children = null;
        }


        /** -------------- SETUP PARAMETERS --------------
         *
         * */
        final int DEFAULT_PROTECTION_LEVEL = 1; // Levels = {1, 2, 3}
        int DEFAULT_DEFENDER_VALUE = 100;
        final int DEFAULT_ATTACKER_VALUE = 100;
        final int nodesToProtect = 3;
        final boolean smartProtect = true;
        final boolean conditionalNode = false;

        /**
         *  ----------------- END -------------------------
         */

        if(DEFAULT_PROTECTION_LEVEL == 3)
            DEFAULT_DEFENDER_VALUE = DEFAULT_DEFENDER_VALUE - nodesToProtect;

        for(int a = 0 ; a < 20; a++) {
            logger.info("Starting Game");
            double defenderValue = DEFAULT_DEFENDER_VALUE;
            double attackerValue = DEFAULT_ATTACKER_VALUE;


            int countRounds = 0;
            List<Vertex> protectedNodes = new ArrayList<>();
            List<Vertex> attackedNodes = new ArrayList<>();
            int successfullyProtectedNodes = 0;
            int failedToProtect = 0;


            logger.info("---------- Setup ----------");
            logger.info("Start Value Defender: " + defenderValue);
            logger.info("Start Value Attacker: " + attackerValue);


            List<Vertex> all = getAllVertices();
            logger.info("Amount of vertices: " + all.size());
            Vertex start = all.get(0);

            logger.info("---------- Starting Simulation " + a + " ----------");
            while (defenderValue != 0 && attackerValue != 0) {
                logger.info("Round " + countRounds);


                // ----------------- Attackers Move ---------------------------
                Vertex attackerMove = null;
                if(algorithmn.equals("RandomChoice"))
                    attackerMove = attackerChooseMoveRANDOM(start);

                if(algorithmn.equals("ForwardPropagate")) {
                    attackerMove = forwardPropagateGreedy(start);
                    if(attackerMove.id == -100) {
                        break;
                    }
                }

                if(attackerMove == null)
                    throw new RuntimeException("Algorithmn must either be 'RandomChoice' or 'MiniMax' or 'ForwardPropagate'");

                System.out.println("AttackerMove: " + attackerMove.id);

                // ------------------ Defenders Move --------------------------

                List<Vertex> protect;
                if(smartProtect)
                    protect = chooseRandomSmartToProtect(nodesToProtect);
                else
                    protect = chooseRandomToProtect(nodesToProtect);

                for (int i = 0; i < protect.size(); i++) {
                    logger.info("Defender protects: Node with label <" + protect.get(i).label + "> and ID <" + protect.get(i).id + ">");
                }


                if(DEFAULT_PROTECTION_LEVEL == 1)
                        defenderValue -= nodesToProtect;
                if(DEFAULT_PROTECTION_LEVEL == 2)
                        defenderValue -= nodesToProtect/2;

                //defenderValue -= nodesToProtect/3;

                // Conditional Nodes
                if(conditionalNode) {
                    if (!attackedNodes.isEmpty()) {
                        if (attackerMove.getId() == 54) {
                            if (!(attackedNodes.contains(getNodeById(51))
                                    || attackedNodes.contains(getNodeById(60))
                                    || attackedNodes.contains(getNodeById(59)))) {
                                attackerValue = 0;
                                System.out.println("Attacker lost!");
                                logger.info("~~~~ Result: ");
                                logger.info("Defender Value: " + defenderValue);
                                logger.info("Attacker Value: " + attackerValue);
                                break;
                            }
                        }
                        if (attackerMove.getId() == 52) {
                            if (!attackedNodes.contains(getNodeById(39)))
                                attackerValue = 0;
                            logger.info("~~~~ Result: ");
                            logger.info("Defender Value: " + defenderValue);
                            logger.info("Attacker Value: " + attackerValue);
                            System.out.println("Attacker lost!");
                            break;
                        }
                    }
                }

                attackerValue = attackerValue - attackerMove.getCost();
                attackedNodes.add(attackerMove);
                logger.info("Attacker attacks Node with label <" + attackerMove.label + "> and ID <" + attackerMove.id + ">");
                start = attackerMove;

                // --------------- evaluate moves ----------------------
                if(isLeaf(attackerMove)) {
                    //leafList.remove(attackerMove);
                    //this.removeEdge(attackerMove);
                    //this.removeVertex(attackerMove);

                    boolean isProtectedNode = checkIfDefended(attackerMove, protect);
                    if (isProtectedNode) {
                        attackerValue -= attackerMove.getNodeValue();
                        attackerMove.count++;
                        defenderValue += attackerMove.getNodeValue();
                        protectedNodes.add(attackerMove);
                        successfullyProtectedNodes++;
                        System.out.println("-------Defender protects!");
                        logger.info("Successfully protected!");
                    } else {
                        attackerValue += attackerMove.getNodeValue();
                        attackerMove.count++;
                        System.out.println("-------Attacker gets: " + attackerMove.getNodeValue());
                        defenderValue -= attackerMove.getNodeValue();

                        if (isLeaf(attackerMove)) {
                            failedToProtect++;
                            logger.info("Failed to protected node!");
                        }
                    }
                }

                logger.info("~~~~ Result: ");
                logger.info("Defender Value: " + defenderValue);
                logger.info("Attacker Value: " + attackerValue);


                if (defenderValue <= 0) {
                    break;
                }

                if (attackerValue <= 0) {
                    break;
                }
                countRounds++;
                logger.info("---------------------- next round ----------------------");
            }

            logger.info("\n");
            logger.info("########## RESULTS ################");
            if(defenderValue <= 0)
                logger.info("Winner: Attacker");
            if(attackerValue <= 0)
                logger.info("Winner: Defender");
            logger.info("Value of Defender: " + defenderValue);
            logger.info("Value of Attacker: " + attackerValue);
            logger.info("Played rounds: " + countRounds);
            logger.info("Successfully protected nodes: " + successfullyProtectedNodes);
            logger.info("Unsuccessfully protected nodes: " + failedToProtect);

            logger.info("END OF SIMULATION");
            logger.info("\n");
            logger.info("\n");
            logger.info("\n");
        }
        logger.info("END OF EXPERIMENT");

        System.out.println("----Simulated ended succesfully!");
        handler.close();
    }

    /**
     * For testing the simulation
     * @param args
     */

    public static void main(String[] args) throws IOException {
        String logPath = "logs.txt";
        Graph g = createGraph();
        g.setLeafValues(4);

        //g.simulate(3, "ForwardPropagate", false, logPath, 6);
        g.simulate("ForwardPropagate", logPath);
    }
}

