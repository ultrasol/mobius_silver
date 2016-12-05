//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2016 uniCenta & previous Openbravo POS works
//    https://unicenta.com
//
//    This file is part of uniCenta oPOS
//
//    uniCenta oPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   uniCenta oPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.
///    Foundation, Inc., 51 Franklin Street, Fifth floor, Boston, MA  02110-1301  USA

package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import java.awt.Component;
import java.util.UUID;
import javax.swing.JPanel;

/**
 *
 * @author JG uniCenta
 */
public final class TaxCustCategoriesEditor extends JPanel implements EditorRecord {
    
    private Object m_oId;
    
    /** Creates new form taxEditor
     * @param dirty */
    public TaxCustCategoriesEditor(DirtyManager dirty) {
        initComponents();

        m_jName.getDocument().addDocumentListener(dirty);
        
        writeValueEOF();
    }

    /**
     *
     */
    @Override
    public void writeValueEOF() {
        m_oId = null;
        m_jName.setText(null);
        m_jName.setEnabled(false);
    }

    /**
     *
     */
    @Override
    public void writeValueInsert() {
        m_oId = UUID.randomUUID().toString();
        m_jName.setText(null);
        m_jName.setEnabled(true);
    }

    /**
     *
     * @param value
     */
    @Override
    public void writeValueDelete(Object value) {

        Object[] taxcustcat = (Object[]) value;
        m_oId = taxcustcat[0];
        m_jName.setText(Formats.STRING.formatValue(taxcustcat[1]));
        m_jName.setEnabled(false);
    }    

    /**
     *
     * @param value
     */
    @Override
    public void writeValueEdit(Object value) {

        Object[] taxcustcat = (Object[]) value;
        m_oId = taxcustcat[0];
        m_jName.setText(Formats.STRING.formatValue(taxcustcat[1]));
        m_jName.setEnabled(true);
    }

    /**
     *
     * @return
     * @throws BasicException
     */
    @Override
    public Object createValue() throws BasicException {
        
        Object[] taxcustcat = new Object[2];

        taxcustcat[0] = m_oId;
        taxcustcat[1] = m_jName.getText();

        return taxcustcat;
    }

    /**
     *
     * @return
     */
    @Override
    public Component getComponent() {
        return this;
    }
    
    /**
     *
     */
    @Override
    public void refresh() {
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();

        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText(AppLocal.getIntString("label.name")); // NOI18N
        jLabel2.setToolTipText("");
        add(jLabel2);
        jLabel2.setBounds(20, 20, 80, 25);

        m_jName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        add(m_jName);
        m_jName.setBounds(100, 20, 200, 25);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField m_jName;
    // End of variables declaration//GEN-END:variables

}
