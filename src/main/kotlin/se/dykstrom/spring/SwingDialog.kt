/*
 * Copyright (C) 2018 Johan Dykstrom
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.dykstrom.spring

import org.jdesktop.application.Action
import java.awt.BorderLayout
import java.awt.GridBagConstraints.HORIZONTAL
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JDialog
import javax.swing.JTextField
import javax.swing.border.EmptyBorder

class SwingDialog(application: BsafApplication) : JDialog() {

    private lateinit var textField: JTextField

    init {
        val actions = application.context.getActionMap(this)

        configure(name = "dialog") {
            panel(name = "panel") {
                +BorderLayout.CENTER
                layout = GridBagLayout()
                border = EmptyBorder(50, 50, 50, 50)

                textField = textField(name = "field") {
                    +gbc(gridx = 1, gridy = 0, weightx = 1.0, fill = HORIZONTAL, insets = Insets(0, 5, 0, 5))
                }

                label(name = "label") {
                    +gbc(0, 0)
                    labelFor = textField
                }

                button(name = "button") {
                    +gbc(2, 0)
                    action = actions.get("closeDialog")
                }
            }
        }

        application.injectComponents<SwingDialog>(this)

        pack()
    }

    @Action
    fun closeDialog() {
        println("Text: ${textField.text}")
        this@SwingDialog.isVisible = false
    }
}
