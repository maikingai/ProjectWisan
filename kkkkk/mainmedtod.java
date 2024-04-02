import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class mainmedtod extends JFrame{
    Title title;
    protected Frame_dijkstra frame_dijkstra;
    FiniteAutomatonGUI finiteAutomatonGUI;
    Frame_Kruskal frame_Kruskal;
    Frame_Prim frame_Prim;
    
    


    Container cp;

    

    





    public mainmedtod(){

        cp = getContentPane();
        



        title = new Title();
        frame_dijkstra = new Frame_dijkstra();
        finiteAutomatonGUI = new FiniteAutomatonGUI();
        frame_Kruskal = new Frame_Kruskal();
        frame_Prim = new Frame_Prim();
        

        cp.add(title);
        // cp.add(frame);
        // cp.add(finiteAutomatonGUI);

        title.addMouseListener(new NavigationTitle());



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("spanning tree");
        setLocation(0,0);
        pack();
        setResizable(false);
        setVisible(true);





    }


    private class NavigationTitle extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(title.Kruskal.getBounds().contains(e.getPoint()) && title.Kruskal.isShowing()){
            
                System.out.println("==> Kruskal Button's clicked");
                cp.remove(title);
                Frame_Kruskal frame_Kruskal = new Frame_Kruskal();
                frame_Kruskal.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame_Kruskal.setVisible(true);
                System.out.println("-----------------------------");
                System.out.println("Kruskal's showing");

            }else if(title.Prim.getBounds().contains(e.getPoint()) && title.Prim.isShowing()){
                System.out.println("==> Prim Button's clicked");
                cp.remove(title);
                Frame_Prim frame_Prim = new Frame_Prim();
                frame_Prim.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame_Prim.setVisible(true);
                System.out.println("-----------------------------");
                System.out.println("Prim's showing");

            }else if(title.Dijkstra.getBounds().contains(e.getPoint()) && title.Dijkstra.isShowing()){
                System.out.println("==> Dijkstra Button's clicked");
                cp.remove(title);
                title.setVisible(false);
                Frame_dijkstra frame_dijkstra = new Frame_dijkstra();
                frame_dijkstra.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame_dijkstra.setVisible(true);
                System.out.println("-----------------------------");
                System.out.println("Dijkstra's showing");

            }else if(title.Finite_automata.getBounds().contains(e.getPoint()) && title.Finite_automata.isShowing()){
                System.out.println("==> Finite Button's clicked");
                cp.remove(title);
                FiniteAutomatonGUI finiteAutomatonGUI = new FiniteAutomatonGUI();
                finiteAutomatonGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
                finiteAutomatonGUI.setVisible(true);
                System.out.println("-----------------------------");
                System.out.println("Finite's showing");
            }
        }
    }



public static void main(String[] args) {
    new mainmedtod();
    
}
    
}

