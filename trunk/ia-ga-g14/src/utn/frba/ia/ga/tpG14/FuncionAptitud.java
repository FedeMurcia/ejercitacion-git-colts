package utn.frba.ia.ga.tpG14;

public interface FuncionAptitud {
	public static final double APTITUD_INDIVIDUO_INVALIDO = -1d;

	public double evaluar(Object individuo);
}
