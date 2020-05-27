package modelo.dao.mapper;

import java.util.List;

import modelo.pojo.IOPort;

/**
 * Interface del mapper
 * @author mique
 *
 */
public interface IOPortMapper {

	public void addIOPortEntry(IOPort ioport);

	public List<IOPort> getIOPorts(int id);

	public IOPort getLastIO(int id);

}
