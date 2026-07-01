package rentalps;
public class PS5 extends Konsol {

    public PS5(String kodeKonsol) {
        super(kodeKonsol, "PlayStation 5");
    }

    @Override
    public double getTarifPerJam() {
        return 15000;
    }
}