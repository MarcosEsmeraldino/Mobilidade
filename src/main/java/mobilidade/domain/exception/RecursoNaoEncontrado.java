
package mobilidade.domain.exception;

/**
 * 404 - Not Found
 *
 * @author marcos
 * @since 04/07/2020
 */
public class RecursoNaoEncontrado extends RuntimeException {

    public RecursoNaoEncontrado() {
    }

    public RecursoNaoEncontrado(String string) {
        super(string);
    }

    public RecursoNaoEncontrado(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public RecursoNaoEncontrado(Throwable thrwbl) {
        super(thrwbl);
    }

    public RecursoNaoEncontrado(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }

}
