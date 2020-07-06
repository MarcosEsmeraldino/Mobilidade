
package mobilidade.domain.exception;

/**
 * 409 - Conflict
 *
 * @author marcos
 * @since 04/07/2020
 */
public class RecursoJaExiste extends RuntimeException {

    public RecursoJaExiste() {
    }

    public RecursoJaExiste(String string) {
        super(string);
    }

    public RecursoJaExiste(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public RecursoJaExiste(Throwable thrwbl) {
        super(thrwbl);
    }

    public RecursoJaExiste(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }

}
