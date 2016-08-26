package greendog.dwr.test;

import org.directwebremoting.ScriptSession;

public abstract class DwrParam {
	public ScriptSession session;
	public abstract boolean validate();
}
