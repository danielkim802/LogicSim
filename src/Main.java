import ComponentError.ComponentInvalidError;

public class Main {

    public static void main(String[] args) throws ComponentInvalidError {
        Circuit circuit = new Circuit();
        int xor1 = circuit.addGate("XOR", 2);
        int xor2 = circuit.addGate("XOR", 2);
        int and1 = circuit.addGate("AND", 2);
        int and2 = circuit.addGate("AND", 2);
        int or1 = circuit.addGate("OR", 2);

        int A = circuit.addIN(true);
        int B = circuit.addIN(true);
        int Cin = circuit.addIN(true);

        int S = circuit.addOUT();
        int Cout = circuit.addOUT();

        circuit.connect(A, 0, xor1, 0);
        circuit.connect(A, 0, and2, 1);
        circuit.connect(B, 0, xor1, 1);
        circuit.connect(B, 0, and2, 0);
        circuit.connect(xor1, 0, xor2, 0);
        circuit.connect(xor1, 0, and1, 0);
        circuit.connect(Cin, 0, xor2, 1);
        circuit.connect(Cin, 0, and1, 1);
        circuit.connect(and1, 0, or1, 0);
        circuit.connect(and2, 0, or1, 1);

        circuit.connect(xor2, 0, S, 0);
        circuit.connect(or1, 0, Cout, 0);

        circuit.evaluate();
        circuit.clear();

        Circuit notchain = new Circuit();
        int prev = notchain.addIN(false);
        int out = notchain.addOUT();
        for (int i = 0; i < 1000000; i ++) {
            int newnot = notchain.addGate("NOT", 1);
            notchain.connect(prev, 0, newnot, 0);
            prev = newnot;
        }
        notchain.connect(prev, 0, out, 0);
        notchain.evaluate();
        System.out.println(notchain.getValue(out, 0));
    }
}
