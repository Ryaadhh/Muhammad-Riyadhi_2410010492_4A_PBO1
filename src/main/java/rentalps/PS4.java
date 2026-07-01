package rentalps;
public class PS4 extends Konsol {

    public PS4(String kodeKonsol) {
        super(kodeKonsol, "PlayStation 4");
    }

    @Override
    public double getTarifPerJam() {
        return 8000;
    }
}