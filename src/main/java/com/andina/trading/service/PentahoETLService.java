import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelInterface;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.stereotype.Service;

@Service
public class PentahoETLService {

    private static final String TRANSFORMATION_PATH = "src/main/resources/Transformation 1.ktr";

    public void ejecutarTransformacion() {
        LogChannelInterface logChannel = new LogChannel("PentahoETLService");

        try {
            // Cargar el archivo de transformación
            TransMeta transMeta = new TransMeta(TRANSFORMATION_PATH);

            // Crear y ejecutar la transformación
            Trans trans = new Trans(transMeta);
            trans.execute(null);
            trans.waitUntilFinished();

            if (trans.getErrors() > 0) {
                logChannel.logError("Hubo un error al ejecutar la transformación.");
                throw new RuntimeException("Hubo un error al ejecutar la transformación.");
            }

            logChannel.logBasic("Transformación ejecutada correctamente");

        } catch (KettleException e) {
            logChannel.logError("Error al ejecutar la transformación", e);
            throw new RuntimeException("Error al ejecutar la transformación", e);
        }
    }
}
