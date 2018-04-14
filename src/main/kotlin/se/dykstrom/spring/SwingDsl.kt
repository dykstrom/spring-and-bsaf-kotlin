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

import java.awt.GridBagConstraints
import java.awt.Insets
import javax.swing.*

/*
 * A mini DSL for creating Swing user interfaces.
 */

class JLABEL(private val _parent: JPANEL) : JLabel() {
    operator fun Any.unaryPlus() = _parent.add(this@JLABEL, this)
}

class JBUTTON(private val _parent: JPANEL) : JButton() {
    operator fun Any.unaryPlus() = _parent.add(this@JBUTTON, this)
}

class JTEXTFIELD(private val _parent: JPANEL) : JTextField() {
    operator fun Any.unaryPlus() = _parent.add(this@JTEXTFIELD, this)
}

class JPANEL(private val _parent: JDialog?) : JPanel() {
    operator fun Any.unaryPlus() = _parent?.add(this@JPANEL, this)
}

/**
 * Creates a button in a panel.
 */
inline fun JPANEL.button(name: String? = null, block: JBUTTON.() -> Unit = {}) =
        JBUTTON(this).apply {
            name?.let { this.name = name }
            block()
        }

/**
 * Creates a label in a panel.
 */
inline fun JPANEL.label(name: String? = null, block: JLABEL.() -> Unit = {}) =
        JLABEL(this).apply {
            name?.let { this.name = name }
            block()
        }

/**
 * Creates a text field in a panel.
 */
inline fun JPANEL.textField(name: String? = null, block: JTEXTFIELD.() -> Unit = {}) =
        JTEXTFIELD(this).apply {
            name?.let { this.name = name }
            block()
        }

/**
 * Creates a panel in a dialog.
 */
inline fun JDialog.panel(name: String? = null, block: JPANEL.() -> Unit = {}) =
        JPANEL(this).apply {
            name?.let { this.name = name }
            block()
        }

/**
 * Creates a stand-alone panel.
 */
inline fun panel(name: String? = null, block: JPANEL.() -> Unit = {}) =
        JPANEL(null).apply {
            name?.let { this.name = name }
            block()
        }

/**
 * Configures a JDialog with a name, and an initialization block.
 */
inline fun JDialog.configure(name: String? = null, block: JDialog.() -> Unit = {}) =
        apply {
            name?.let { this.name = name }
            block()
        }

/**
 * Creates a `GridBagConstraints` object from the given arguments.
 */
fun gbc(
        gridx: Int = -1,
        gridy: Int = -1,
        gridwidth: Int = 1,
        gridheight: Int = 1,
        weightx: Double = 0.0,
        weighty: Double = 0.0,
        anchor: Int = 10,
        fill: Int = 0,
        insets: Insets = Insets(0, 0, 0, 0),
        ipadx: Int = 0,
        ipady: Int = 0
) = GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady)
