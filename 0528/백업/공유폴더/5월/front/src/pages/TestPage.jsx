import React from 'react';
import MainSearchBar from 'components/MainSearchBar';
import SPI from '../public/img1.jpg';

import 'styles/Pages/TestPage.scss';

function TestPage() {
	return (
		<div>
			<MainSearchBar />
			<table className="imgtable">
				<tbody>
					<tr key="row-1">
						<td key="cell-1-1">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-1-2">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-1-3">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-1-4">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-1-5">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
					</tr>
					<tr key="row-2">
						<td key="cell-2-1">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-2-2">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-2-3">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-2-4">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-2-5">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
					</tr>
					<tr key="row-3">
						<td key="cell-3-1">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-3-2">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-3-3">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-3-4">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-3-5">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
					</tr>
					<tr key="row-4">
						<td key="cell-4-1">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-4-2">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-4-3">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-4-4">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
						<td key="cell-4-5">
							<a href="page=1">
								<img src={SPI} alt="" />
							</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	);
}

export default TestPage;
