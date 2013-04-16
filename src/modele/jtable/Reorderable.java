/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.jtable;

/**
 *
 * @author btssio
 */
public interface Reorderable {
    /**
     *
     * @param fromIndex
     * @param toIndex
     */
    public void reorder(int fromIndex, int toIndex);
}
