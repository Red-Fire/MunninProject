package modelo.Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Beans.Eval_Detalle_Lista_Bean;
import util.ClassConexion;

public class Eval_Detalle_Lista_Dao extends ClassConexion {

    public Connection conn = null;
    public Statement st = null;
    public ResultSet rs = null;

    public boolean encontrado = false;
    public boolean listo = false;

    public long id_eval_detalle_lista;
    public int calificacion_eval_detalle_lista;
    public String observarcion_eval_detalle_lista;
    public long id_eval_version_general_eval_detalle_lista;
    public long id_detalle_lista_eval_detalle_lista;

    public Eval_Detalle_Lista_Dao(Eval_Detalle_Lista_Bean Evalu_Deta_lista) {
        super();
        conn = this.obtenerConexion();
        id_eval_detalle_lista = Evalu_Deta_lista.getId_eval_detalle_lista();
        calificacion_eval_detalle_lista = Evalu_Deta_lista.getCalificacion_eval_detalle_lista();
        observarcion_eval_detalle_lista = Evalu_Deta_lista.getObservarcion_eval_detalle_lista();
        id_eval_version_general_eval_detalle_lista = Evalu_Deta_lista.getId_eval_version_general_eval_detalle_lista();
        id_detalle_lista_eval_detalle_lista = Evalu_Deta_lista.getId_detalle_lista_eval_detalle_lista();

    }

    public boolean insertar_eval_detalle_lista() {
        try {
            CallableStatement cst = conn.prepareCall("{call insertar_eval_detalle_lista (?,?,?,?,?)}");
            cst.setLong(1, id_eval_detalle_lista);
            cst.setInt(2, calificacion_eval_detalle_lista);
            cst.setString(3, observarcion_eval_detalle_lista);
            cst.setLong(4, id_eval_detalle_lista);
            cst.setLong(5, id_detalle_lista_eval_detalle_lista);
            cst.execute();
        } catch (SQLException e) {
        }
        return listo;
    }

    public boolean editar_eval_detalle_lista() {
        try {
            CallableStatement cst = conn.prepareCall("{call editar_eval_detalle_lista (?,?,?,?,?)}");
             // Se envian parametros del procedimiento almacenado
            cst.setLong(1, id_eval_detalle_lista);
            cst.setInt(2, calificacion_eval_detalle_lista);
            cst.setString(3, observarcion_eval_detalle_lista);
            cst.setLong(4, id_eval_detalle_lista);
            cst.setLong(5, id_detalle_lista_eval_detalle_lista);
            // Ejecuta el procedimiento almacenado
            cst.execute();
        } catch (SQLException e) {
        }
        return listo;
    }

    public boolean eliminar_eval_detalle_lista() {
        try {
            CallableStatement cst = conn.prepareCall("{call eliminar_eval_detalle_lista (?)}");
             // Se envian parametros del procedimiento almacenado
            cst.setLong(1, id_eval_detalle_lista);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            
        } catch (SQLException e) {
        }
        return listo;
    }

    public Eval_Detalle_Lista_Bean ver_eval_detalle_lista() {
        Eval_Detalle_Lista_Bean edi = null;
        try {
            CallableStatement cst = conn.prepareCall("{call ver_eval_detalle_lista(?)}");
            // Se envian parametros del procedimiento almacenado
            cst.setLong(1, id_eval_detalle_lista);
            // Definimos los tipos de los parametros de salida del procedimiento almacenado
            cst.registerOutParameter(2, java.sql.Types.INTEGER);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            cst.registerOutParameter(4, java.sql.Types.VARCHAR);
            cst.registerOutParameter(5, java.sql.Types.VARCHAR);
           
            // Ejecuta el procedimiento almacenado
            cst.execute();
            // Se obtienen la salida del procedimineto almacenado                
            edi = new Eval_Detalle_Lista_Bean(id_eval_detalle_lista, cst.getInt(2), cst.getString(3), cst.getLong(4), cst.getLong(5));
        } catch (SQLException e) {
        }
        return edi;
    }
}
