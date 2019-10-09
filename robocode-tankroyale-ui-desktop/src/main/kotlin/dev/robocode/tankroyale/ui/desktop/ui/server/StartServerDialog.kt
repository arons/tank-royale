package dev.robocode.tankroyale.ui.desktop.ui.server

import dev.robocode.tankroyale.ui.desktop.extensions.JComponentExt.addButton
import dev.robocode.tankroyale.ui.desktop.extensions.JComponentExt.addLabel
import dev.robocode.tankroyale.ui.desktop.extensions.JComponentExt.showWarning
import dev.robocode.tankroyale.ui.desktop.extensions.JTextFieldExt.setInputVerifier
import dev.robocode.tankroyale.ui.desktop.settings.ServerSettings
import dev.robocode.tankroyale.ui.desktop.ui.MainWindow
import dev.robocode.tankroyale.ui.desktop.ui.ResourceBundles
import dev.robocode.tankroyale.ui.desktop.ui.ResourceBundles.MESSAGES
import dev.robocode.tankroyale.ui.desktop.ui.battle.GameTypeComboBox
import dev.robocode.tankroyale.ui.desktop.ui.components.JLimitedTextField
import dev.robocode.tankroyale.ui.desktop.util.Event
import kotlinx.serialization.ImplicitReflectionSerializer
import net.miginfocom.swing.MigLayout
import java.awt.Dimension
import java.awt.EventQueue
import javax.swing.JButton
import javax.swing.JDialog
import javax.swing.JPanel
import javax.swing.UIManager

@ImplicitReflectionSerializer
object StartServerDialog : JDialog(MainWindow, ResourceBundles.UI_TITLES.get("start_server_dialog")) {

    init {
        defaultCloseOperation = DISPOSE_ON_CLOSE

        size = Dimension(180, 150)

        setLocationRelativeTo(null) // center on screen

        contentPane.add(StartServerPanel)
    }
}

@ImplicitReflectionSerializer
private object StartServerPanel : JPanel(MigLayout("fill")) {

    // Private events
    private val onOk = Event<JButton>()
    private val onCancel = Event<JButton>()

    private val portTextField = JLimitedTextField(5, "${ServerSettings.DEFAULT_PORT}")

    private val gameTypeComboBox = GameTypeComboBox()

    private var port: Int = ServerSettings.DEFAULT_PORT

    init {
        addLabel("port", "right")
        add(portTextField, "wrap")

        addLabel("game_type", "right")
        add(gameTypeComboBox, "wrap")

        addButton("ok", onOk, "center")
        addButton("cancel", onCancel, "center")

        portTextField.setInputVerifier { portVerifier() }

        onCancel.subscribe { StartServerDialog.dispose() }

        onOk.subscribe {
            StartServerCommand(
                port = portTextField.text.toInt(),
                gameType = gameTypeComboBox.gameSetup.gameType
            ).execute()
            StartServerDialog.dispose()
        }
    }

    private fun portVerifier(): Boolean {
        val port: Int? = try {
            portTextField.text.trim().toInt()
        } catch (e: NumberFormatException) {
            null
        }
        val valid = port != null && port in 1024..65535
        if (valid) {
            StartServerPanel.port = port!!
        } else {
            showWarning(String.format(MESSAGES.get("port_must_be_in_range"), 1024, 65535))
            portTextField.text = "${ServerSettings.DEFAULT_PORT}"
        }
        return valid
    }
}

@ImplicitReflectionSerializer
private fun main() {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

    EventQueue.invokeLater {
        StartServerDialog.isVisible = true
    }
}