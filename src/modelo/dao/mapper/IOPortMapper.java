package modelo.dao.mapper;

import java.util.List;

import modelo.pojo.IOPort;

public interface IOPortMapper {

	public void addIOPortEntry(IOPort ioport);

	public List<IOPort> getIOPorts(int id);

	public IOPort getLastIO(int id);

}
