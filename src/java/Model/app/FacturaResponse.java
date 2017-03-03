/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.app;

/**
 *
 * @author HP
 */
public class FacturaResponse {
    private String lineaFactura[];
    private String message;
    private int code;
    private boolean success;
    private int operation;

    /**
     * @return the lineaFactura
     */
    public String[] getLineaFactura() {
        return lineaFactura;
    }

    /**
     * @param lineaFactura the lineaFactura to set
     */
    public void setLineaFactura(String[] lineaFactura) {
        this.lineaFactura = lineaFactura;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the operation
     */
    public int getOperation() {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(int operation) {
        this.operation = operation;
    }
    
}
