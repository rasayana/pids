package pids.model;
import pids.core.Device;
import pids.core.Sector;
@SuppressWarnings("rawtypes")
abstract class ODevice<T extends Device> extends OToggle<T> implements Device<T> {
    ODevice(Sector otdr, String id) {
        super(otdr, id);
    }
    private String ip;
    private String user;
    private String password;
    @SuppressWarnings("unchecked")
	@Override
    public T prepareDevice(String ip, String user,String password) {
    	this.ip = ip;
    	this.user = user;
    	this.password = password;
        return (T) this;
    }
    @SuppressWarnings("unchecked")
	@Override
    public T activateDevice() {
        return (T) this;
    }
    @SuppressWarnings("unchecked")
	@Override
    public T deActivateDevice() {
        return (T) this;
    }
	@Override
	public String ip() {
		return ip;
	}
	@Override
	public String user() {
		return user;
	}
	@Override
	public String password() {
		return password;
	}
}