/*
 * Jirecon, the Jitsi recorder container.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package org.jitsi.jirecon.test;

import org.jitsi.jirecon.recorder.JireconRecorderManager;
import org.jitsi.jirecon.recorder.JireconRecorderManagerImpl;
import org.jitsi.jirecon.session.JireconSessionManager;
import org.jitsi.jirecon.session.JireconSessionManagerImpl;
import org.jitsi.jirecon.session.JireconSessionStatus;
import org.jitsi.jirecon.utils.JireconFactory;
import org.jitsi.jirecon.utils.JireconFactoryImpl;
import org.jitsi.jirecon.utils.JireconMessageReceiver;
import org.jitsi.jirecon.utils.JireconMessageSender;
import org.jivesoftware.smack.XMPPException;

import junit.framework.TestCase;

public class TestJireconRecorderManagerImpl
    extends TestCase
{
    private static String hostname = "jitmeet.example.com";

    private static int port = 5222;

    private static JireconSessionManager smgr = new JireconSessionManagerImpl(
        hostname, port);

    private static JireconRecorderManager rmgr =
        new JireconRecorderManagerImpl();

    @Override
    protected void setUp()
    {
        try
        {
            smgr.init();
            rmgr.init();
            
            // Binding message receiver and sender
            ((JireconMessageSender) smgr)
                .addReceiver((JireconMessageReceiver) rmgr);
        }
        catch (XMPPException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void testOpenAndCloseJingleSession()
    {
        final String cf1 = "kkuwmnzrnslzbyb9";

        try
        {
            smgr.openJingleSession(cf1);
            rmgr.startRecording(cf1);
        }
        catch (XMPPException e1)
        {
            e1.printStackTrace();
        }

        try
        {
            Thread.sleep(200000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println(smgr.getSessionInfo(cf1)
            .getSessionStatus());

        smgr.closeJingleSession(cf1);
    }

    @Override
    protected void tearDown()
    {
        smgr.uninit();
        rmgr.uninit();
    }
}
